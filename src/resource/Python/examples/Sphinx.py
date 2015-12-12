from java.lang import String
from org.myrobotlab.service import Sphinx
from org.myrobotlab.service import Runtime
 
# create ear and mouth
ear = Runtime.createAndStart("ear","Sphinx")
mouth = Runtime.createAndStart("mouth","MarySpeech")
 
# start listening for the words we are interested in
ear.startListening("hello world|happy monkey|go forward|stop")

ear.addCommand("hello world", "python", "helloWorld")
 
# set up a message route from the ear --to--> python method "heard"
# ear.addListener("recognized", python.name, "heard"); 
ear.addComfirmations("yes","correct","ya") 
ear.addNegations("no","wrong","nope","nah")
# this method is invoked when something is 
# recognized by the ear - in this case we
# have the mouth "talk back" the word it recognized
#def heard(phrase):
#      mouth.speak("you said " + phrase)
#      print "heard ", phrase
     
# prevent infinite loop - this will suppress the
# recognition when speaking - default behavior
# when attaching an ear to a mouth :)
ear.addMouth(mouth)
mouth.addEar(ear)


def helloWorld():
    print "Hello world in python invoked."
