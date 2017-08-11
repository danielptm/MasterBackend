### What is the purpose of this module?
This purpose of this module is to provide bean classes to be used by the different layers
of the application. This is an effort to refactor and make the code more maintainable, but
may not be super important to get done at this point. Because most of the data in the database,
can be returned. The real sensitive data such as employee password and stuff is stored in another table
which is never returned. The most important thing is to return the objects without the '_', and
to not return sensitve data points to the guest homepage. It may be that the best solution
for now is to just make sure that the sensitive areas are nulled when returning to the 
guethomepage.

####priorities

1) Create beans to be mapped and returned by the service layer.

2) Create beans that will be used at the api level. these will have the @JsonProperty annotation.

###When should beans module be merged with master?
When priority 1 is completed, and unit tests are passing, and integration tests are passing. That is
running the angular app against it, then this can be merged. Same thing for priority
number 2. But number 1 and number 2 can be merge seperately.

###Todo:

1) 

In service level of each method, map the database employee object to and employee service level object.
Then return this object to the api layer.
    
2)

Deals service, map database deals object to service deals object.

3) 

Event service,

4)

Recommendation service

