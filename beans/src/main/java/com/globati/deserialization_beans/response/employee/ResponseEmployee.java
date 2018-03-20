package com.globati.deserialization_beans.response.employee;

import java.util.List;

public class ResponseEmployee {

    private Long id;
    private String firstName;
    private String image;
    private String image2;
    private String image3;
    private String email;
    private String paypalEmail;
    private String about;
    private String welcomeMail;
    private String recruitmentMail;
    private String instagramUserName;
    private String instagramUserId;
    private String instagramToken;
    private Double propLat;
    private Double propLong;
    private String street;
    private String city;
    private String country;
    private String display;
    private String globatiUsername;
    private boolean facebookProfile;
    private List<ResponseRecommendation> recommendations;
    private List<ResponseEvent> events;
    private List<ResponseFlight> flightBookings;
    private List<ResponseHotel> hotelBookings;
    private List<ResponseBlog> responseBlogs;
    private String apiKey;
    private Integer profileVisits;
    private List<ResponseTip> responseTips;
    private boolean isFacebookProfileCreated = false;

    public ResponseEmployee(){}

    public ResponseEmployee(Long id, String firstName, String image, String image2, String image3, String email, String paypalEmail, String about, String welcomeMail, String recruitmentMail, String instagramUserName, String instagramUserId, String instagramToken, Double propLat, Double propLong, String street, String city, String country, String display, String globatiUsername, boolean facebookProfile, List<ResponseRecommendation> recommendations, List<ResponseEvent> events, List<ResponseHotel> hotels, String apikey, Integer profileVisits, List<ResponseBlog> blogs, List<ResponseTip> responseTips) {
        this.id = id;
        this.firstName = firstName;
        this.image = image;
        this.image2 = image2;
        this.image3 = image3;
        this.email = email;
        this.paypalEmail = paypalEmail;
        this.about = about;
        this.welcomeMail = welcomeMail;
        this.recruitmentMail = recruitmentMail;
        this.instagramUserName = instagramUserName;
        this.instagramUserId = instagramUserId;
        this.instagramToken = instagramToken;
        this.propLat = propLat;
        this.propLong = propLong;
        this.street = street;
        this.city = city;
        this.country = country;
        this.display = display;
        this.globatiUsername = globatiUsername;
        this.facebookProfile = facebookProfile;
        this.recommendations = recommendations;
        this.events = events;
        this.hotelBookings = hotels;
        this.apiKey = apikey;
        this.profileVisits = profileVisits;
        this.responseBlogs = blogs;
        this.responseTips = responseTips;
    }

    public ResponseEmployee(Long id, String firstName, String image, String image2, String image3, String email, String paypalEmail, String about, String welcomeMail, String recruitmentMail, String instagramUserName, String instagramUserId, String instagramToken, Double propLat, Double propLong, String street, String city, String country, String display, String globatiUsername, boolean facebookProfile, List<ResponseRecommendation> recommendations, List<ResponseEvent> events, String apikey, List<ResponseFlight> flightBookings, List<ResponseHotel> hotelBookings, Integer profileVisits,  List<ResponseBlog> blogs, List<ResponseTip> responseTips, boolean isFacebookProfileCreated) {
        this.id = id;
        this.firstName = firstName;
        this.image = image;
        this.image2 = image2;
        this.image3 = image3;
        this.email = email;
        this.paypalEmail = paypalEmail;
        this.about = about;
        this.welcomeMail = welcomeMail;
        this.recruitmentMail = recruitmentMail;
        this.instagramUserName = instagramUserName;
        this.instagramUserId = instagramUserId;
        this.instagramToken = instagramToken;
        this.propLat = propLat;
        this.propLong = propLong;
        this.street = street;
        this.city = city;
        this.country = country;
        this.display = display;
        this.globatiUsername = globatiUsername;
        this.facebookProfile = facebookProfile;
        this.recommendations = recommendations;
        this.events = events;
        this.apiKey = apikey;
        this.flightBookings = flightBookings;
        this.hotelBookings = hotelBookings;
        this.profileVisits = profileVisits;
        this.responseBlogs = blogs;
        this.responseTips = responseTips;
        this.isFacebookProfileCreated = isFacebookProfileCreated;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getImage() {
        return image;
    }

    public String getImage2() {
        return image2;
    }

    public String getImage3() {
        return image3;
    }

    public String getEmail() {
        return email;
    }

    public String getPaypalEmail() {
        return paypalEmail;
    }

    public String getAbout() {
        return about;
    }

    public String getWelcomeMail() {
        return welcomeMail;
    }

    public String getRecruitmentMail() {
        return recruitmentMail;
    }

    public String getInstagramUserName() {
        return instagramUserName;
    }

    public String getInstagramUserId() {
        return instagramUserId;
    }

    public String getInstagramToken() {
        return instagramToken;
    }

    public Double getPropLat() {
        return propLat;
    }

    public Double getPropLong() {
        return propLong;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getDisplay() {
        return display;
    }

    public String getGlobatiUsername() {
        return globatiUsername;
    }

    public boolean isFacebookProfile() {
        return facebookProfile;
    }

    public List<ResponseRecommendation> getRecommendations() {
        return recommendations;
    }

    public List<ResponseEvent> getEvents() {
        return events;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPaypalEmail(String paypalEmail) {
        this.paypalEmail = paypalEmail;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setWelcomeMail(String welcomeMail) {
        this.welcomeMail = welcomeMail;
    }

    public void setRecruitmentMail(String recruitmentMail) {
        this.recruitmentMail = recruitmentMail;
    }

    public void setInstagramUserName(String instagramUserName) {
        this.instagramUserName = instagramUserName;
    }

    public void setInstagramUserId(String instagramUserId) {
        this.instagramUserId = instagramUserId;
    }

    public void setInstagramToken(String instagramToken) {
        this.instagramToken = instagramToken;
    }

    public void setPropLat(Double propLat) {
        this.propLat = propLat;
    }

    public void setPropLong(Double propLong) {
        this.propLong = propLong;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public void setGlobatiUsername(String globatiUsername) {
        this.globatiUsername = globatiUsername;
    }

    public void setFacebookProfile(boolean facebookProfile) {
        this.facebookProfile = facebookProfile;
    }

    public void setRecommendations(List<ResponseRecommendation> recommendations) {
        this.recommendations = recommendations;
    }

    public void setEvents(List<ResponseEvent> events) {
        this.events = events;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public List<ResponseFlight> getFlightBookings() {
        return flightBookings;
    }

    public void setFlightBookings(List<ResponseFlight> flightBookings) {
        this.flightBookings = flightBookings;
    }

    public List<ResponseHotel> getHotelBookings() {
        return hotelBookings;
    }

    public void setHotelBookings(List<ResponseHotel> hotelBookings) {
        this.hotelBookings = hotelBookings;
    }

    public Integer getProfileVisits() {
        return profileVisits;
    }

    public void setProfileVisits(Integer profileVisits) {
        this.profileVisits = profileVisits;
    }

    public List<ResponseBlog> getResponseBlogs() {
        return responseBlogs;
    }

    public void setResponseBlogs(List<ResponseBlog> responseBlogs) {
        this.responseBlogs = responseBlogs;
    }

    public List<ResponseTip> getResponseTips() {
        return responseTips;
    }

    public void setResponseTips(List<ResponseTip> responseTips) {
        this.responseTips = responseTips;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isFacebookProfileCreated() {
        return isFacebookProfileCreated;
    }

    public void setFacebookProfileCreated(boolean facebookProfileCreated) {
        isFacebookProfileCreated = facebookProfileCreated;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResponseEmployee{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append(", image2='").append(image2).append('\'');
        sb.append(", image3='").append(image3).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", paypalEmail='").append(paypalEmail).append('\'');
        sb.append(", about='").append(about).append('\'');
        sb.append(", welcomeMail='").append(welcomeMail).append('\'');
        sb.append(", recruitmentMail='").append(recruitmentMail).append('\'');
        sb.append(", instagramUserName='").append(instagramUserName).append('\'');
        sb.append(", instagramUserId='").append(instagramUserId).append('\'');
        sb.append(", instagramToken='").append(instagramToken).append('\'');
        sb.append(", propLat=").append(propLat);
        sb.append(", propLong=").append(propLong);
        sb.append(", street='").append(street).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", display='").append(display).append('\'');
        sb.append(", globatiUsername='").append(globatiUsername).append('\'');
        sb.append(", facebookProfile=").append(facebookProfile);
        sb.append(", recommendations=").append(recommendations);
        sb.append(", events=").append(events);
        sb.append(", flightBookings=").append(flightBookings);
        sb.append(", hotelBookings=").append(hotelBookings);
        sb.append(", apiKey='").append(apiKey).append('\'');
        sb.append(", profileVisits=").append(profileVisits);
        sb.append('}');
        return sb.toString();
    }
}
