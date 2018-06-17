package com.globati.mail.beans;

public class GlobatiReminder {

    private String hostelName;

    public GlobatiReminder(String hostelName) {
        this.hostelName = hostelName;
    }

    public String getMessage(){
        return  "<!DOCTYPE html>"+
                "<html>"+
                "<head>"+
                "<meta charset=\"UTF-8\">"+
                "<style>"+
                "  @import url('https://fonts.googleapis.com/css?family=Lato');"+
                "  body {"+
                "    background-color: whitesmoke;"+
                "    font-size: 18px;"+
                "  }"+
                "  .container{"+
                "    font-family: Lato;"+
                "    flex-wrap: wrap;"+
                "    display: flex;"+
                "    justify-content: center;"+
                "    text-align: center;"+
                "    margin: 0 auto;"+
                "    width: 70%;"+
                "    flex-direction: column;"+
                "    padding: 10%;"+
                "  }"+
                "  .globatiLogo{"+
                "    width: 150px;"+
                "    height: auto;"+
                "  }"+
                "  .appstore {"+
                "    width: 200px;"+
                "    height: auto;"+
                "    margin-top: 10px;"+
                "  }"+
                "  .buttonContainer {"+
                "    flex-wrap: wrap;"+
                "    display: flex;"+
                "    flex-direction: row;"+
                "    justify-content: center;"+
                "  }"+
                "  .description{"+
                "    margin-bottom: 30px;"+
                "  }"+
                ""+
                "  .email {"+
                "    color: #f37652;"+
                "  }"+
                "</style>"+
                "<title>Title of the document</title>"+
                "</head>"+
                "<body>"+
                "<div class=\"container\">"+
                "  <h1>"+
                "    "+hostelName+" has a profile on the Globati mobile app."+
                "  </h1>"+
                "  <div class=\"description\">"+
                "    If your guests want your recommendations with google maps and translated to their language,"+
                "    you can tell them to download the app here."+
                "  </div>"+
                "  <div>"+
                "    <img class=\"globatiLogo\" src=\"https://s3.eu-central-1.amazonaws.com/globatiimages/splash/Logo_and_Name.png\">"+
                "  </div>"+
                "  <div class=\"buttonContainer\">"+
                "    <a href=\"https://itunes.apple.com/us/app/globati/id1378112364?mt=8\">"+
                "      <img class=\"appstore\" src=\"https://s3.eu-central-1.amazonaws.com/globatiimages/splash/appstorebutton.png\">"+
                "    </a>"+
                "    <a href=\"https://play.google.com/store/apps/details?id=com.globati\">"+
                "      <img class=\"appstore\" src=\"https://s3.eu-central-1.amazonaws.com/globatiimages/splash/googleplaybutton.png\">"+
                "    </a>"+
                "  </div>"+
                "  <div>"+
                "    <p>Feeling shy? Here is a <a class=\"email\" href=\"https://s3.eu-central-1.amazonaws.com/globatiimages/splash/poster.jpg\">poster</a> you can put up in the hostel instead.</p>"+
                "    <p>If you want to edit your recommendations or add some, then email us <a class=\"email\" href=\"mailto:daniel@globati.com\">here.</a></p>"+
                "  </div>"+
                "</div>"+
                "</body>"+
                ""+
                "</html>";
    }

}
