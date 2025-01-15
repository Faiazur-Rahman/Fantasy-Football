package com.brainstation.fantasyfootball.service;

import com.brainstation.fantasyfootball.common.ServiceResponse;
import com.brainstation.fantasyfootball.exception.ResourceNotFoundException;
import com.brainstation.fantasyfootball.model.dto.EventDTO;
import com.brainstation.fantasyfootball.model.dto.JoinAnEventDTO;
import com.brainstation.fantasyfootball.model.entity.*;
import com.brainstation.fantasyfootball.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;

//    @Autowired
//    private ServiceResponse<?> serviceResponse;
    @Autowired
    private IEventUserPointRepository eventUserPointRepository;
    @Autowired
    private ITournamentRepository tournamentRepository;
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    private List<User> getEventUser(Long eventId){
        return eventUserPointRepository.getAllByEventId(eventId)
                .stream()
                .map(eventUserPoint -> userRepository.findById(eventUserPoint.getUserId()).get())
                .collect(Collectors.toList());
    }
    public Map<Long,Integer> getEventDetails(List<Event> eventList){
        Map<Long,Integer> eventDetails = new HashMap<>();
        for (Event event: eventList) {
            eventDetails.put(event.getId(),eventUserPointRepository.getAllByEventId(event.getId()).size());
        }
        return eventDetails;
    }
    public List<Long> getJoinedInfo(Principal user){
        User user1 = userRepository.findByUsername(user.getName());
        List<Long> list = eventUserPointRepository.getAllByUserId(user1.getId())
                .stream()
                .map(EventUserPoint::getEventId)
                .collect(Collectors.toList());
        return list;
    }
    public Long getEventUserPoint(Long eventId, Principal loggedInUser){
        User user = userRepository.findByUsername(loggedInUser.getName());
        return eventUserPointRepository.getEventUserPointByEventIdAndUserId(eventId,user.getId()).getUserBalance();
    }

    public ServiceResponse<?> createEvent(EventDTO eventDTO, Principal loggedInUser){
        ServiceResponse<?> serviceResponse=new ServiceResponse<>();
        User user = userRepository.findByUsername(loggedInUser.getName());
        Tournament tournament = tournamentRepository.findById(4L).get();
        if(user==null){
            serviceResponse.setHasError(true);
            serviceResponse.setErrorMsg("Sorry "+loggedInUser.getName()+" is not found in database ! ");
            return serviceResponse;
        }
        else if(ObjectUtils.isEmpty(tournament)){
            serviceResponse.setHasError(true);
            serviceResponse.setErrorMsg("Sorry Tournament is not Found ");
            return serviceResponse;
        }
        else {
            Event event = new Event();
            event.setEventName(eventDTO.getEventName());
            event.setEventDescription(eventDTO.getEventDescription());

            String uuid = UUID.randomUUID().toString();
            uuid.replace("-", "");
            event.setEventCode(uuid);
            event.setEventCreator(user.getId());
            event.setStartRound(1);
            event.setTournament(tournament);
            eventRepository.save(event);
            tournament.addEvent(event);
            tournamentRepository.save(tournament);
            setEventUserPointRepository(user, event);
            serviceResponse.setHasError(false);
            serviceResponse.setSuccessMsg("Event " + event.getEventName() + " is created Successfully");
            return serviceResponse;
        }
    }
    public boolean checkIfTheUserIsAMember(Long eventId, Principal loggedInUser){
        User user = userRepository.findByUsername(loggedInUser.getName());
        return eventUserPointRepository.existsEventUserPointByEventIdAndUserId(eventId,user.getId());
    }

    public ServiceResponse<?> joinAnEvent(String eventCode, Principal user){

        ServiceResponse<?> serviceResponse=new ServiceResponse<>();
        User user1 = userRepository.findByUsername(user.getName());
        boolean eventExists=eventRepository.existsEventByEventCode(eventCode);

        if(!eventExists){
            serviceResponse.setExist(false);
            serviceResponse.setHasError(true);
            serviceResponse.setErrorMsg("Sorry EventCode is Wrong");
        }
        else{
            Event event = eventRepository.findEventByEventCode(eventCode);
            setEventUserPointRepository(user1,event);
            serviceResponse.setHasError(false);
            serviceResponse.setSuccessMsg("Congratulations You joined the "+event.getEventName()+" successfully !");

        }

        return serviceResponse;
    }
    public ServiceResponse<?> enterInThisEvent(Long eventId,Principal user){
        ServiceResponse<?> serviceResponse=new ServiceResponse<>();
        User user1 = userRepository.findByUsername(user.getName());
        boolean eventExists=eventRepository.existsById(eventId);

        if(!eventExists){
            serviceResponse.setExist(false);
            serviceResponse.setHasError(true);
            serviceResponse.setAuthorization(false);
            serviceResponse.setErrorMsg("Sorry No Such Event Found");
        }
        else{
            Event event=eventRepository.findById(eventId).get();
            boolean userExistsInThisEvent=eventUserPointRepository.existsEventUserPointByEventIdAndUserId(event.getId(), user1.getId());
            if(!userExistsInThisEvent){
                serviceResponse.setExist(true);
                serviceResponse.setHasError(true);
                serviceResponse.setAuthorization(false);
                serviceResponse.setErrorMsg("Sorry You are not a member of this event !");
            }
            else{
                serviceResponse.setExist(true);
                serviceResponse.setAuthorization(true);
                serviceResponse.setHasError(false);
            }
        }
        return serviceResponse;

    }
    public ServiceResponse<?> leaveThisEvent(Long eventId, Principal loggedInUser){
        ServiceResponse<?> serviceResponse=new ServiceResponse<>();
        boolean eventExists=eventRepository.existsById(eventId);
        User user = userRepository.findByUsername(loggedInUser.getName());
        boolean authorizedUser=eventUserPointRepository.existsEventUserPointByEventIdAndUserId(eventId,user.getId());
        if(!eventExists){
            serviceResponse.setExist(false);
            serviceResponse.setHasError(true);
            serviceResponse.setAuthorization(false);
            serviceResponse.setErrorMsg("Sorry No Event Found !");
        }
        else if (!authorizedUser) {
            serviceResponse.setExist(true);
            serviceResponse.setHasError(true);
            serviceResponse.setAuthorization(false);
            serviceResponse.setErrorMsg("Sorry You are not member of this event !");
        }
        else{
            serviceResponse.setExist(true);
            serviceResponse.setHasError(false);
            serviceResponse.setAuthorization(true);
            serviceResponse.setSuccessMsg("you left the "+eventRepository.findById(eventId).get().getEventName()+" league Successfully !");
            eventUserPointRepository.delete(eventUserPointRepository.getEventUserPointByEventIdAndUserId(eventId, user.getId()));
        }
        return serviceResponse;
    }
    private void setEventUserPointRepository(User user, Event event){
        EventUserPoint eventUserPoint = new EventUserPoint();
        eventUserPoint.setUserId(user.getId());
        Event event1 = eventRepository.findEventByEventCode(event.getEventCode());
        eventUserPoint.setEventId(event1.getId());
        eventUserPoint.setPoint(0l);
        eventUserPoint.setUserBalance(100l);
        eventUserPoint.setUsername(user.getUsername());
        eventUserPointRepository.save(eventUserPoint);
    }

    public List<User> userRankList(Long eventId) {
        if (!eventRepository.existsById(eventId)){
            throw new ResourceNotFoundException("Not An Event");
        }
        List<User> userList = eventUserPointRepository.getEventUserPointByEventIdOrderByPointDesc(eventId)
                .stream()
                .map((eventUserPoint) ->
                        userRepository.findById(eventUserPoint.getUserId()).orElse(new User())
                )
                .collect(Collectors.toList());
        return userList;
    }
    public Long userPoint(Long eventId,Long userId){
        long point  = userRepository.existsById(userId) && eventRepository.existsById(eventId)
                ? eventUserPointRepository.getEventUserPointByEventIdAndUserId(eventId,userId).getPoint()
                : -1L;
        if (point == -1L)
            throw new ResourceNotFoundException("Not found");
        return point;
    }
    public List<EventUserPoint> getUserRanking(Long eventId){
        return eventUserPointRepository.getEventUserPointByEventIdOrderByPointDesc(eventId);
    }
    public List<EventUserPoint> getUserEventAndPoint(String username){
        return eventUserPointRepository.getEventUserPointByUsername(username);
    }
    public Optional<Event> getEventById(Long id){
       return eventRepository.findById(id);
    }

    public Optional<EventUserPoint> getEventUserByEventIdUserId(Long eventId, Long userId){
        return eventUserPointRepository.getEventUserPoint(eventId,userId);
    }

    public Optional<EventUserPoint> getEventUserByEventIdUsername(Long eventId, String username){
        return eventUserPointRepository.getEventUserPointByUsername(eventId,username);
    }

    public Optional<List<Event>> searchEvent(String name){
          return Optional.ofNullable(eventRepository.findEventsByEventNameContaining(name));
    }
}
