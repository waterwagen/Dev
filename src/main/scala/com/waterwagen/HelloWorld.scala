package com.waterwagen

import scala.actors.Actor._
import scala.actors._

object HelloWorld extends App 
{
	println("Hello World")
	
	// Try defining a function
	def valueFunc(multiplier : Int) = 
	{
		multiplier * 2
	}	
	println(valueFunc(3))
	
	// Try case class (like an enum)
	case class Pet(name:String, species:String)
	val mansbestfriend = new Pet("Fido", "dog")
	val doesntcare = new Pet("Muffles", "cat")
	println("This pet is a " + mansbestfriend.species + " named " + mansbestfriend.name)

	// Try collections
	var pet_list = List(mansbestfriend, doesntcare)
	def fido_finder(pet:Pet) = // defined method
	{
	  if(pet.name == "Fido") 
		  true
	  else
		  false
	}
	println("Does Fido exist=" + pet_list.exists(fido_finder))
	val scruffy_finder = (pet:Pet) => // function literal 
	{
		if(pet.name=="Scruffy")
			true
		else 
			false
	}
	println("Does Scruffy exist=" + pet_list.exists(scruffy_finder))
	println("Does Muffles exist=" + pet_list.exists((pet:Pet) => {if(pet.name=="Muffles")true;else false}))
	pet_list = pet_list :+ new Pet("Scruffy", "dog")
	println("Does Scruffy exist=" + pet_list.exists(scruffy_finder))
	
	// Try a for loop (like For-Each)
	val petlist_printer = (pet_list:List[Pet],header:String) =>
	{
		val buffer = new StringBuffer()
		buffer.append(header)
		for(pet <- pet_list)
			buffer.append(pet + ",")
		buffer.deleteCharAt(buffer.length() - 1)
		buffer.toString()
	}
	println(petlist_printer(pet_list,"Pet list:"))
	val dog_list = pet_list.filter((pet:Pet)=>{if(pet.species=="dog")true;else false})
	println(petlist_printer(dog_list,"Dog list:"))
	
	// Try a range in a for loop
	println("Running through a loop.")
	for(i <- 1 to 3)
	{
		println("Loop iteration " + i)
	}	
	
	// Try equality operators
	val two1 = 2
	val two2 = 2
	assert(two1.equals(two1)) // checks for equality as in java
	assert(two1 == two2) // same as equals() method, unlike Java
	
	val str1 = "blah"
	val str2 = "blah"
	assert(str1.equals(str2)) // checks for equality as in java
	assert(str1 == str2) // same as equals() method, unlike Java
	assert(str1.eq(str2)) // actually checks for same reference, in Java this would be the == operator
	
	// Try some concurrency features
	//Mesage types
	case class RunningStateMessage(shouldRun:Boolean)
	case class PrintStringMessage(str:String)
	class MyFirstActor extends Actor
	{
		def act()
		{
			var running = true
			while(running)
			{
				receive
				{
					case msg:Boolean => println("Received boolean " + msg)
					case msg:String => println("Received string " + msg)
					case msg:RunningStateMessage => println("Received running state message " + msg); running = msg.shouldRun
					case msg:PrintStringMessage => println("Received print string message. String is " + msg.str)
					case _ => println("Unknown message type received.")
				}
			}
		}
	}
	val first_actor = new MyFirstActor()
	first_actor.start
	// send messages to the actor
	first_actor ! "This is a message."
	first_actor ! "This is also a message."
	first_actor ! true
	first_actor ! false
	first_actor ! new PrintStringMessage("I want to print this message.")
	first_actor ! "stop"
	first_actor ! List(1,2,3)
	first_actor ! RunningStateMessage(false) // new keyword is optional (only for case classes?)
	first_actor ! "This message should not be received because the actor was stopped"
	
	// try a shortcut for creating actors
	case class PrintMessage(toPrint:String)
	case class StopMessage()
	val receiving_actor = actor
	{
		var running = true
		while(running)
		{
			receive
			{
				case msg:PrintMessage => println(msg.toPrint)
				case msg:StopMessage => running = false
				case _ => println("Unknown message received.")
			}
		}
	}
	receiving_actor.start()
	val sending_actor = actor 
	{
		receiving_actor ! PrintMessage("Yo!")
		receiving_actor ! PrintMessage("Yo, again!")
		receiving_actor ! StopMessage()
	}
	sending_actor.start()

	// Try some of the Actor methods
	// try an Actor's loop
	val looping_actor = actor
	{
		var counter = 1;
		loop
		{
			if(counter > 5)
				exit()
			println("Loop counter=" + counter)
			counter += 1
		}
	}
	looping_actor.start()
	
	// try and Actor's loopWhile and receiveWithin
	case class StandardMessage(str:String)
	val timeout_actor = actor
	{
		var running = true
		loopWhile(running)
		{
			receiveWithin(2000)
			{
				case msg:StandardMessage => println(msg.str)
				case TIMEOUT => running = false
				case _ => "Unknown message!"
			}
		}
	}
	timeout_actor.start()
	for(counter <- 1 to 3)
		timeout_actor ! StandardMessage("Message " + counter)
	Thread.sleep(3000)
	timeout_actor ! StandardMessage("This message should not be received!")
	
	// Additions to String from the StringOps class
	val str = "blah"
	assert(str.capitalize == "Blah")
	assert(str.head == 'b')
	assert(str.last == 'h')
	assert(str.drop(2) == "ah")
	assert(str.take(2) == "bl")
	
	// Numbers
	assert(99.44.toInt == 99)
	assert(8 * 5 + 2 == 42)
}