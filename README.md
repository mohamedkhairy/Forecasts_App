# Forecast App

<h3> This app was built following Multi-module Architecture (Modularization) 
<h3> Each feature module is built upon the clean Architecture layers packages
<h4> using Gradle version catalog for dependency management
<h4> Jetpack compose for building UI state
<h4> Compose navigation system
<h4> Dagger Hilt<br>
<h4> Room DB<br>
<h4> Github Actios<br>
<h4> Unit Test<br>
<h4> Compose UI Test<be>
<h4> APK --> you can find an APK in the Action completed jobs<be>


  
<br>
<h3> ******* ->  
<h3>The App module</h3>
<h5>app -> have MainActivity, the navigation system of the app, and application class</h5>
<h5>core -> have the most usable components through the app</h5>
<h5>core-database ->it has a unit test for the DAO interface</h5>
<h5>feature.Home -> <br> the home screen, where you can search for city weather, also contains a unit test and Compose UI test for this module</h5>
<h5>feature.Details -> <br> The City forecast details screen, which displays the list of weather related to the City</h5>
<h6>-->(To access daily weather data from the OpenWeatherMap API, a subscription is required, so I used an alternative that returns a list of weather for the selected city.)</h6>



<br>
<h3> *** There is a to-do list for what should be done next. ->
<h5> Remove the hard-coded text on the string.xml file
<h5> Perform a Unit test and UI test on the Details module and ensure they work as expected.
<h5> there's also some enhancement related to the UI



