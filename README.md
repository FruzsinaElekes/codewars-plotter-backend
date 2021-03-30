## Overview

This API serves data for the https://codewars-plotter.herokuapp.com website. Data is retrieved from the public endpoints of the Codewars API (https://dev.codewars.com/). 
Base information about katas (rank, tags, url, category etc.) are stored in a PostrgeSQL database after retrieved.

### Endpoints

The frontend app queries data through RESTful endpoints. 

***users/{username}*** returns a UserSummary object including information on ranks per language and overall rank, skills, clan, leaderboard position, total completed katas.

***users/{username}/plots/{language}*** returns a PlotData object for the requested language with a list of DataPoints (number of completed katas for a given rank)

***users/{usrename}/katas*** returns a list of KataCompletionDetail objects, which combine base information about the kata in question, and information about completion (which languages has the user completed the kata in and date of first completion)

## Technical Details

After cloning the repository, create an application.properties file based on the application.properties.template in 'src/main/resources' folder:
Set up the required parameters for the database connection.

To start the development server locally:

<pre><code>mvn spring-boot:run</code></pre>

By default the server will start at: http://localhost:8080