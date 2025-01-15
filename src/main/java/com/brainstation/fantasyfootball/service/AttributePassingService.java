package com.brainstation.fantasyfootball.service;/*
 * @created 08/11/2022 -11:50
 * @author  Anupam Das
 *
 */

import com.brainstation.fantasyfootball.model.entity.UserImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@Service
public class AttributePassingService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserImageService userImageService;

    public List<String> getLoggedINUserAndImage(HttpServletRequest request){

        List<String> UserAndImageList = new ArrayList<>();
        Principal principal = request.getUserPrincipal();
        String name = principal.getName();
        String baseEncodedData = null;
        Optional<UserImage> userImage = userImageService.getImage(name);
        if(userImage.isPresent()){
            byte[] dataImage = Base64.getEncoder().encode(userImage.get().getData());

            try {
                baseEncodedData = new String(dataImage,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

//            System.out.println(baseEncodedData);
        }
        UserAndImageList.add(name);
        UserAndImageList.add(baseEncodedData);
        return UserAndImageList;
    }


}


