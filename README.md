# Jaquarium

## An interactive, virtual aquarium

***What will the application do?***  
*Jaquarium* will allow you to explore the joys of owning your own aquarium, right from your computer! Intended to be a relaxing respite from the troubles of debugging poorly documented code and computing optimal algorithm speed. Jaquarium is designed to be your escape to an interactive underwater world. Intended for all users looking for a low-stakes lifestyle game.

***Why is this project of interest to you?***  
I really wanted to design a program that involves a creative element. I struggled with worlds programs last term (particularly making graphical representations of functions), so I wanted to get more practice going from concept of object to an image on the screen. I thought a simple rendering of fish swimming in an aquarium would be a good way to dip my toes in the water.

***User Stories***
- As a user, I want to be able to add a fish to my aquarium.
- As a user, I want to be able to remove fish from my aquarium.
- As a user, I want to be able to feed a fish in my aquarium.
- As a user, I want to be able to check if a fish is hungry.
- As a user, I want to be able to print a list of the fish in my aquarium.
- As a user, I want to be able to save my aquarium to file.
- As a user, I want to be able to load my aquarium from file.

***Phase 4: Task 2***
Wed Mar 30 14:03:59 PDT 2022
Fish Nemo added to aquarium.

Wed Mar 30 14:04:03 PDT 2022
Fish Gill added to aquarium.

Wed Mar 30 14:04:06 PDT 2022
Fish Bubbles added to aquarium.

Wed Mar 30 14:04:09 PDT 2022
Fish Gill fed.

Wed Mar 30 14:04:14 PDT 2022
Fish Bubbles removed from aquarium.

Wed Mar 30 14:04:19 PDT 2022
Fish Marvin added to aquarium.

Wed Mar 30 14:04:21 PDT 2022
List of fish names printed to dialogue box.

Wed Mar 30 14:04:24 PDT 2022
Checked health of Marvin.

Wed Mar 30 14:04:30 PDT 2022
Fish Gill removed from aquarium.

Wed Mar 30 14:04:32 PDT 2022
Aquarium game saved.

Wed Mar 30 14:04:34 PDT 2022
Aquarium game loaded.

***Phase 4: Task 3***
- The first consideration I would like to make has to do with my AquariumGame (AG) class and the single responsibility 
    principle. My AG class has become somewhat of a kitchen sink for random methods. I would like to refactor the
    class to break up some of its functionality to other classes to improve the AG class cohesion. ButtonPanel could 
    also benefit from this same kind of refactoring.
  - For example, I could create a new Food class to hold all the methods related to feeding the fish
    (such as feedFish, reduceHealth, etc). I would like to add some graphical representations of  
    feeding the fish, and this would also be easier to do once the food class is its own entity.
- Looking at the implementation of various methods in AquariumGame, there is a LOT of repeated code. This makes the 
        code somewhat unreadable, and hard to update. Code for feedFish, removeFish, getFishHealth, 
        getFishNames could be abstracted away into its own method to make the code more manageable. 
- My code is in dire need of some exception handling. As it is right now, if you try to perform an action on a fish 
    that does not exist, then nothing happens. I would like to add some exception handling so, if a user requests to
    feed or remove a fish that does not exist, that a DoesNotExist exception would be thrown, and the GUI would
    prompt the user to try again (ie. to name a different fish to perform the action on).
- Looking at the UML diagram, there are a lot of panel classes associated with the Main class. I wonder if I could 
    instantiate all of the panel classes in a separate jframe class, and then just have a field for the jframe object
    in my main class. I think this would make more since anyway, since my main class has become very messy.