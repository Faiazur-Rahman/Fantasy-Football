function Formation(){
    playerDivCardsId = [];
    selectedPlayerId = [];
    selectedPlayerValues = [];
    $('#showPlayers').empty();
    alert("hi");
    let formation = document.getElementById("formation").value;
    document.getElementById("default").style.display="none";

    if([[${formation}]]=="4-3-3"){
        document.getElementById("content").style.display = "none";
        document.getElementById("default").style.display="block";
    } else if([[${formation}]]=="4-4-2"){
        document.getElementById("content").style.display = "block";
        document.getElementById("content").innerHTML = "  <div class=\"container-fluid\">\n" +
            "                               <div class=\"row\">\n" +
            "\n" +
            "                                   <div class=\"col-md-12\" style=' background-color: #00FF40;'>\n" +
            "                                       <table class=\"col-md-12\" style=\"height:800px\">\n" +
            "                                           <tr >\n" +
            "                                               <th colspan=\"20\" >\n" +
            "                                                   <a class=\"d-flex justify-content-center \" id=\"goalKeeper\" onClick=\"call(this.id)\" >\n" +
                                                                        '<span th:switch="${userTeam.size()>0}">' +
                                                                       '<div th:case="true" className="card shadow-lg" style="border-radius: 20%;width: 100px;height: 160px">' +

                                                                       '<image style="border-radius: 20%;width: 100px;height: 100px" th:src="${userTeam.get(0).getPicture()}"></image>' +
                                                                       '<div className="card-body" style="margin: -12px">' +
                                                                           '<span th:text="${userTeam.get(0).getNickname()}"></span>' +
                                                                           '<br>' +
                                                                           '<span th:text="${userTeam.get(0).getPositionType()}"></span>' +
                                                                       '</div>' +
                                                                       '</div>' +
                                                                        '<image th:case="*" className="shadow-lg  " src="/image/jersy.PNG"></image>' +
                                                                       '</span>'+
            "                                                    </a>\n" +
            "                                               </th>\n" +
            "                                           </tr>\n" +
            "                                           <tr>\n" +
            "                                               <th colspan=\"5\">\n" +
            "                                                    <a class=\"d-flex justify-content-center\" id=\"defender1\" onClick=\"call(this.id)\">\n" +
                                                                '<span th:switch="${userTeam.size()>0}">' +
                                                                '<div th:case="true" className="card shadow-lg" style="border-radius: 20%;width: 100px;height: 160px">' +

                                                                '<image style="border-radius: 20%;width: 100px;height: 100px" th:src="${userTeam.get(1).getPicture()}"></image>' +
                                                                '<div className="card-body" style="margin: -12px">' +
                                                                '<span th:text="${userTeam.get(1).getNickname()}"></span>' +
                                                                '<br>' +
                                                                '<span th:text="${userTeam.get(1).getPositionType()}"></span>' +
                                                                '</div>' +
                                                                '</div>' +
                                                                '<image th:case="*" className="shadow-lg  " src="/image/jersy.PNG"></image>' +
                                                                '</span>'+
            "                                               </a>\n" +
            "                                               </th>\n" +
            "                                               <th colspan=\"5\">\n" +
            "                                                   <a class=\"d-flex justify-content-center\" id=\"defender2\" onClick=\"call(this.id)\">\n" +
                                                                    '<span th:switch="${userTeam.size()>0}">' +
                                                                    '<div th:case="true" className="card shadow-lg" style="border-radius: 20%;width: 100px;height: 160px">' +

                                                                    '<image style="border-radius: 20%;width: 100px;height: 100px" th:src="${userTeam.get(2).getPicture()}"></image>' +
                                                                    '<div className="card-body" style="margin: -12px">' +
                                                                    '<span th:text="${userTeam.get(2).getNickname()}"></span>' +
                                                                    '<br>' +
                                                                    '<span th:text="${userTeam.get(2).getPositionType()}"></span>' +
                                                                    '</div>' +
                                                                    '</div>' +
                                                                    '<image th:case="*" className="shadow-lg  " src="/image/jersy.PNG"></image>' +
                                                                    '</span>'+
            "                                                   </a>\n" +
            "                                               </th>\n" +
            "                                               <th colspan=\"5\">\n" +
            "                                                   <a class=\"d-flex justify-content-center\" id=\"defender3\" onClick=\"call(this.id)\">\n" +
                                                                        '<span th:switch="${userTeam.size()>0}">' +
                                                                        '<div th:case="true" className="card shadow-lg" style="border-radius: 20%;width: 100px;height: 160px">' +

                                                                        '<image style="border-radius: 20%;width: 100px;height: 100px" th:src="${userTeam.get(3).getPicture()}"></image>' +
                                                                        '<div className="card-body" style="margin: -12px">' +
                                                                        '<span th:text="${userTeam.get(3).getNickname()}"></span>' +
                                                                        '<br>' +
                                                                        '<span th:text="${userTeam.get(3).getPositionType()}"></span>' +
                                                                        '</div>' +
                                                                        '</div>' +
                                                                        '<image th:case="*" className="shadow-lg  " src="/image/jersy.PNG"></image>' +
                                                                        '</span>'+
            "                                                   </a>\n" +
            "                                               </th>\n" +
            "                                               <th colspan=\"5\">\n" +
            "                                                   <a class=\"d-flex justify-content-center\" id=\"defender4\" onClick=\"call(this.id)\">\n" +
                                                                        '<span th:switch="${userTeam.size()>0}">' +
                                                                        '<div th:case="true" className="card shadow-lg" style="border-radius: 20%;width: 100px;height: 160px">' +

                                                                        '<image style="border-radius: 20%;width: 100px;height: 100px" th:src="${userTeam.get(4).getPicture()}"></image>' +
                                                                        '<div className="card-body" style="margin: -12px">' +
                                                                        '<span th:text="${userTeam.get(4).getNickname()}"></span>' +
                                                                        '<br>' +
                                                                        '<span th:text="${userTeam.get(4).getPositionType()}"></span>' +
                                                                        '</div>' +
                                                                        '</div>' +
                                                                        '<image th:case="*" className="shadow-lg  " src="/image/jersy.PNG"></image>' +
                                                                        '</span>'+
            "                                                   </a>\n" +
            "                                               </th>\n" +
            "                                           </tr>\n" +
            "                                           <tr>\n" +
            "                                               <th colspan=\"5\"><a class=\"d-flex justify-content-center\" id=\"mid1\" onClick=\"call(this.id)\">\n" +
                                                            '<span th:switch="${userTeam.size()>0}">' +
                                                            '<div th:case="true" className="card shadow-lg" style="border-radius: 20%;width: 100px;height: 160px">' +

                                                            '<image style="border-radius: 20%;width: 100px;height: 100px" th:src="${userTeam.get(5).getPicture()}"></image>' +
                                                            '<div className="card-body" style="margin: -12px">' +
                                                            '<span th:text="${userTeam.get(5).getNickname()}"></span>' +
                                                            '<br>' +
                                                            '<span th:text="${userTeam.get(5).getPositionType()}"></span>' +
                                                            '</div>' +
                                                            '</div>' +
                                                            '<image th:case="*" className="shadow-lg  " src="/image/jersy.PNG"></image>' +
                                                            '</span>'+
            "                                                   </a></th>\n" +
            "                                               <th colspan=\"5\"><a class=\"d-flex justify-content-center\" id=\"mid2\" onClick=\"call(this.id)\">\n" +
                                                            '<span th:switch="${userTeam.size()>0}">' +
                                                            '<div th:case="true" className="card shadow-lg" style="border-radius: 20%;width: 100px;height: 160px">' +

                                                            '<image style="border-radius: 20%;width: 100px;height: 100px" th:src="${userTeam.get(6).getPicture()}"></image>' +
                                                            '<div className="card-body" style="margin: -12px">' +
                                                            '<span th:text="${userTeam.get(6).getNickname()}"></span>' +
                                                            '<br>' +
                                                            '<span th:text="${userTeam.get(6).getPositionType()}"></span>' +
                                                            '</div>' +
                                                            '</div>' +
                                                            '<image th:case="*" className="shadow-lg  " src="/image/jersy.PNG"></image>' +
                                                            '</span>'+
            "                                               </a></th>\n" +
            "                                               <th colspan=\"5\"><a class=\"d-flex justify-content-center\" id=\"mid3\" onClick=\"call(this.id)\">\n" +
                                                                '<span th:switch="${userTeam.size()>0}">' +
                                                                '<div th:case="true" className="card shadow-lg" style="border-radius: 20%;width: 100px;height: 160px">' +

                                                                '<image style="border-radius: 20%;width: 100px;height: 100px" th:src="${userTeam.get(7).getPicture()}"></image>' +
                                                                '<div className="card-body" style="margin: -12px">' +
                                                                '<span th:text="${userTeam.get(7).getNickname()}"></span>' +
                                                                '<br>' +
                                                                '<span th:text="${userTeam.get(7).getPositionType()}"></span>' +
                                                                '</div>' +
                                                                '</div>' +
                                                                '<image th:case="*" className="shadow-lg  " src="/image/jersy.PNG"></image>' +
                                                                '</span>'+
            "                                               </a></th>\n" +
            "                                               <th colspan=\"5\"><a class=\"d-flex justify-content-center\" id=\"mid4\" onClick=\"call(this.id)\">\n" +
                                                                '<span th:switch="${userTeam.size()>0}">' +
                                                                '<div th:case="true" className="card shadow-lg" style="border-radius: 20%;width: 100px;height: 160px">' +

                                                                '<image style="border-radius: 20%;width: 100px;height: 100px" th:src="${userTeam.get(8).getPicture()}"></image>' +
                                                                '<div className="card-body" style="margin: -12px">' +
                                                                '<span th:text="${userTeam.get(8).getNickname()}"></span>' +
                                                                '<br>' +
                                                                '<span th:text="${userTeam.get(8).getPositionType()}"></span>' +
                                                                '</div>' +
                                                                '</div>' +
                                                                '<image th:case="*" className="shadow-lg  " src="/image/jersy.PNG"></image>' +
                                                                '</span>'+
            "                                                   </a></th>\n" +
            "                                           </tr>\n" +
            "                                           <tr>\n" +
            "                                               <th colspan=\"10\"><a class=\"d-flex justify-content-center\" id=\"forward1\" onClick=\"call(this.id)\">\n" +
                                                            '<span th:switch="${userTeam.size()>0}">' +
                                                            '<div th:case="true" className="card shadow-lg" style="border-radius: 20%;width: 100px;height: 160px">' +

                                                            '<image style="border-radius: 20%;width: 100px;height: 100px" th:src="${userTeam.get(9).getPicture()}"></image>' +
                                                            '<div className="card-body" style="margin: -12px">' +
                                                            '<span th:text="${userTeam.get(9).getNickname()}"></span>' +
                                                            '<br>' +
                                                            '<span th:text="${userTeam.get(9).getPositionType()}"></span>' +
                                                            '</div>' +
                                                            '</div>' +
                                                            '<image th:case="*" className="shadow-lg  " src="/image/jersy.PNG"></image>' +
                                                            '</span>'+
            "                                               </a></th>\n" +
            "                                               <th colspan=\"10\"><a class=\"d-flex justify-content-center\" id=\"forward2\" onClick=\"call(this.id)\">\n" +
                                                                '<span th:switch="${userTeam.size()>0}">' +
                                                                '<div th:case="true" className="card shadow-lg" style="border-radius: 20%;width: 100px;height: 160px">' +

                                                                '<image style="border-radius: 20%;width: 100px;height: 100px" th:src="${userTeam.get(10).getPicture()}"></image>' +
                                                                '<div className="card-body" style="margin: -12px">' +
                                                                '<span th:text="${userTeam.get(10).getNickname()}"></span>' +
                                                                '<br>' +
                                                                '<span th:text="${userTeam.get(10).getPositionType()}"></span>' +
                                                                '</div>' +
                                                                '</div>' +
                                                                '<image th:case="*" className="shadow-lg  " src="/image/jersy.PNG"></image>' +
                                                                '</span>'+
            "                                               </a></th>\n" +
            "                                           </tr>\n" +
            "                                       </table>\n" +
            "                                   </div>\n" +
            "\n" +
            "                               </div>\n" +
            "                           </div>\n" +
            "                           <input type=\"hidden\" name=\"position\" id=\"position\" value=\"\"/>"
    } else{
        document.getElementById("content").style.display = "block";
        document.getElementById("content").innerHTML = "<div class=\"container-fluid\">\n" +
            "                               <div class=\"row\">\n" +
            "\n" +
            "                                   <div class=\"col-md-12\" style='background-color: #00FF40;'>\n" +
            "                                       <table class=\"col-md-12\" style=\"height:800px\">\n" +
            "                                           <tr >\n" +
            "                                               <th colspan=\"20\" >\n" +
            "                                                   <a class=\"d-flex justify-content-center \" id=\"goalKeeper\" onClick=\"call(this.id)\" >\n" +
                                                                '<span th:switch="${userTeam.size()>0}">' +
                                                                '<div th:case="true" className="card shadow-lg" style="border-radius: 20%;width: 100px;height: 160px">' +

                                                                '<image style="border-radius: 20%;width: 100px;height: 100px" th:src="${userTeam.get(0).getPicture()}"></image>' +
                                                                '<div className="card-body" style="margin: -12px">' +
                                                                '<span th:text="${userTeam.get(0).getNickname()}"></span>' +
                                                                '<br>' +
                                                                '<span th:text="${userTeam.get(0).getPositionType()}"></span>' +
                                                                '</div>' +
                                                                '</div>' +
                                                                '<image th:case="*" className="shadow-lg  " src="/image/jersy.PNG"></image>' +
                                                                '</span>'+
            "                                                    </a>\n" +
            "                                               </th>\n" +
            "                                           </tr>\n" +
            "                                           <tr>\n" +
            "                                               <th colspan=\"5\">\n" +
            "                                                    <a class=\"d-flex justify-content-center\" id=\"defender1\" onClick=\"call(this.id)\">\n" +
                                                                    '<span th:switch="${userTeam.size()>0}">' +
                                                                    '<div th:case="true" className="card shadow-lg" style="border-radius: 20%;width: 100px;height: 160px">' +

                                                                    '<image style="border-radius: 20%;width: 100px;height: 100px" th:src="${userTeam.get(0).getPicture()}"></image>' +
                                                                    '<div className="card-body" style="margin: -12px">' +
                                                                    '<span th:text="${userTeam.get(0).getNickname()}"></span>' +
                                                                    '<br>' +
                                                                    '<span th:text="${userTeam.get(0).getPositionType()}"></span>' +
                                                                    '</div>' +
                                                                    '</div>' +
                                                                    '<image th:case="*" className="shadow-lg  " src="/image/jersy.PNG"></image>' +
                                                                    '</span>'+
            "                                                   </a>\n" +
            "                                               </th>\n" +
            "                                               <th colspan=\"10\">\n" +
            "                                                   <a class=\"d-flex justify-content-center\" id=\"defender2\" onClick=\"call(this.id)\">\n" +
                                                                '<span th:switch="${userTeam.size()>0}">' +
                                                                '<div th:case="true" className="card shadow-lg" style="border-radius: 20%;width: 100px;height: 160px">' +

                                                                '<image style="border-radius: 20%;width: 100px;height: 100px" th:src="${userTeam.get(0).getPicture()}"></image>' +
                                                                '<div className="card-body" style="margin: -12px">' +
                                                                '<span th:text="${userTeam.get(0).getNickname()}"></span>' +
                                                                '<br>' +
                                                                '<span th:text="${userTeam.get(0).getPositionType()}"></span>' +
                                                                '</div>' +
                                                                '</div>' +
                                                                '<image th:case="*" className="shadow-lg  " src="/image/jersy.PNG"></image>' +
                                                                '</span>'+
            "                                                   </a>\n" +
            "                                               </th>\n" +
            "                                               <th colspan=\"5\">\n" +
            "                                                   <a class=\"d-flex justify-content-center\" id=\"defender3\" onClick=\"call(this.id)\">\n" +
                                                                '<span th:switch="${userTeam.size()>0}">' +
                                                                '<div th:case="true" className="card shadow-lg" style="border-radius: 20%;width: 100px;height: 160px">' +

                                                                '<image style="border-radius: 20%;width: 100px;height: 100px" th:src="${userTeam.get(0).getPicture()}"></image>' +
                                                                '<div className="card-body" style="margin: -12px">' +
                                                                '<span th:text="${userTeam.get(0).getNickname()}"></span>' +
                                                                '<br>' +
                                                                '<span th:text="${userTeam.get(0).getPositionType()}"></span>' +
                                                                '</div>' +
                                                                '</div>' +
                                                                '<image th:case="*" className="shadow-lg  " src="/image/jersy.PNG"></image>' +
                                                                '</span>'+
            "                                                   </a>\n" +
            "                                               </th>\n" +
            "                                           </tr>\n" +
            "                                           <tr>\n" +
            "                                               <th colspan=\"5\"><a class=\"d-flex justify-content-center\" id=\"mid1\" onClick=\"call(this.id)\">\n" +
                                                            '<span th:switch="${userTeam.size()>0}">' +
                                                            '<div th:case="true" className="card shadow-lg" style="border-radius: 20%;width: 100px;height: 160px">' +

                                                            '<image style="border-radius: 20%;width: 100px;height: 100px" th:src="${userTeam.get(0).getPicture()}"></image>' +
                                                            '<div className="card-body" style="margin: -12px">' +
                                                            '<span th:text="${userTeam.get(0).getNickname()}"></span>' +
                                                            '<br>' +
                                                            '<span th:text="${userTeam.get(0).getPositionType()}"></span>' +
                                                            '</div>' +
                                                            '</div>' +
                                                            '<image th:case="*" className="shadow-lg  " src="/image/jersy.PNG"></image>' +
                                                            '</span>'+
            "                                               </a></th>\n" +
            "                                               <th colspan=\"5\"><a class=\"d-flex justify-content-center\" id=\"mid2\" onClick=\"call(this.id)\">\n" +
                                                            '<span th:switch="${userTeam.size()>0}">' +
                                                            '<div th:case="true" className="card shadow-lg" style="border-radius: 20%;width: 100px;height: 160px">' +

                                                            '<image style="border-radius: 20%;width: 100px;height: 100px" th:src="${userTeam.get(0).getPicture()}"></image>' +
                                                            '<div className="card-body" style="margin: -12px">' +
                                                            '<span th:text="${userTeam.get(0).getNickname()}"></span>' +
                                                            '<br>' +
                                                            '<span th:text="${userTeam.get(0).getPositionType()}"></span>' +
                                                            '</div>' +
                                                            '</div>' +
                                                            '<image th:case="*" className="shadow-lg  " src="/image/jersy.PNG"></image>' +
                                                            '</span>'+
            "                                               </a></th>\n" +
            "                                               <th colspan=\"5\"><a class=\"d-flex justify-content-center\" id=\"mid3\" onClick=\"call(this.id)\">\n" +
                                                            '<span th:switch="${userTeam.size()>0}">' +
                                                            '<div th:case="true" className="card shadow-lg" style="border-radius: 20%;width: 100px;height: 160px">' +

                                                            '<image style="border-radius: 20%;width: 100px;height: 100px" th:src="${userTeam.get(0).getPicture()}"></image>' +
                                                            '<div className="card-body" style="margin: -12px">' +
                                                            '<span th:text="${userTeam.get(0).getNickname()}"></span>' +
                                                            '<br>' +
                                                            '<span th:text="${userTeam.get(0).getPositionType()}"></span>' +
                                                            '</div>' +
                                                            '</div>' +
                                                            '<image th:case="*" className="shadow-lg  " src="/image/jersy.PNG"></image>' +
                                                            '</span>'+
            "                                               </a></th>\n" +
            "                                               <th colspan=\"5\"><a class=\"d-flex justify-content-center\" id=\"mid4\" onClick=\"call(this.id)\">\n" +
                                                            '<span th:switch="${userTeam.size()>0}">' +
                                                            '<div th:case="true" className="card shadow-lg" style="border-radius: 20%;width: 100px;height: 160px">' +

                                                            '<image style="border-radius: 20%;width: 100px;height: 100px" th:src="${userTeam.get(0).getPicture()}"></image>' +
                                                            '<div className="card-body" style="margin: -12px">' +
                                                            '<span th:text="${userTeam.get(0).getNickname()}"></span>' +
                                                            '<br>' +
                                                            '<span th:text="${userTeam.get(0).getPositionType()}"></span>' +
                                                            '</div>' +
                                                            '</div>' +
                                                            '<image th:case="*" className="shadow-lg  " src="/image/jersy.PNG"></image>' +
                                                            '</span>'+
            "                                               </a></th>\n" +
            "                                           </tr>\n" +
            "                                           <tr>\n" +
            "                                               <th colspan=\"5\">\n" +
            "                                                    <a class=\"d-flex justify-content-center\" id=\"defender1\" onClick=\"call(this.id)\">\n" +
                                                                    '<span th:switch="${userTeam.size()>0}">' +
                                                                    '<div th:case="true" className="card shadow-lg" style="border-radius: 20%;width: 100px;height: 160px">' +

                                                                    '<image style="border-radius: 20%;width: 100px;height: 100px" th:src="${userTeam.get(0).getPicture()}"></image>' +
                                                                    '<div className="card-body" style="margin: -12px">' +
                                                                    '<span th:text="${userTeam.get(0).getNickname()}"></span>' +
                                                                    '<br>' +
                                                                    '<span th:text="${userTeam.get(0).getPositionType()}"></span>' +
                                                                    '</div>' +
                                                                    '</div>' +
                                                                    '<image th:case="*" className="shadow-lg  " src="/image/jersy.PNG"></image>' +
                                                                    '</span>'+
            "                                                               </a>\n" +
            "                                               </th>\n" +
            "                                               <th colspan=\"10\">\n" +
            "                                                   <a class=\"d-flex justify-content-center\" id=\"defender2\" onClick=\"call(this.id)\">\n" +
                                                                    '<span th:switch="${userTeam.size()>0}">' +
                                                                    '<div th:case="true" className="card shadow-lg" style="border-radius: 20%;width: 100px;height: 160px">' +

                                                                    '<image style="border-radius: 20%;width: 100px;height: 100px" th:src="${userTeam.get(0).getPicture()}"></image>' +
                                                                    '<div className="card-body" style="margin: -12px">' +
                                                                    '<span th:text="${userTeam.get(0).getNickname()}"></span>' +
                                                                    '<br>' +
                                                                    '<span th:text="${userTeam.get(0).getPositionType()}"></span>' +
                                                                    '</div>' +
                                                                    '</div>' +
                                                                    '<image th:case="*" className="shadow-lg  " src="/image/jersy.PNG"></image>' +
                                                                    '</span>'+
            "                                                   </a>\n" +
            "                                               </th>\n" +
            "                                               <th colspan=\"5\">\n" +
            "                                                   <a class=\"d-flex justify-content-center\" id=\"defender3\" onClick=\"call(this.id)\">\n" +
                                                                '<span th:switch="${userTeam.size()>0}">' +
                                                                '<div th:case="true" className="card shadow-lg" style="border-radius: 20%;width: 100px;height: 160px">' +

                                                                '<image style="border-radius: 20%;width: 100px;height: 100px" th:src="${userTeam.get(0).getPicture()}"></image>' +
                                                                '<div className="card-body" style="margin: -12px">' +
                                                                '<span th:text="${userTeam.get(0).getNickname()}"></span>' +
                                                                '<br>' +
                                                                '<span th:text="${userTeam.get(0).getPositionType()}"></span>' +
                                                                '</div>' +
                                                                '</div>' +
                                                                '<image th:case="*" className="shadow-lg  " src="/image/jersy.PNG"></image>' +
                                                                '</span>'+
            "                                                   </a>\n" +
            "                                               </th>\n" +
            "                                           </tr>\n" +
            "                                       </table>\n" +
            "                                   </div>\n" +
            "\n" +
            "                               </div>\n" +
            "                           </div>\n" +
            "                           <input type=\"hidden\" name=\"position\" id=\"position\" value=\"\"/>"
    }
}