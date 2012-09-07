package com.waterwagen

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
}