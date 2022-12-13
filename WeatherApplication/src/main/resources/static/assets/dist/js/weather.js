/*
    Pitts, Dylan
    Last modified: 2/09/2022
    Purpose: Get weather data from goverment weather API using user input
*/

//On button click
$("button").click(function(data) {
    //prevent default action of webpage
    data.preventDefault();
    //set searchbar query as input
    var input = document.querySelector('.form-control').value;
    //Get
    $.ajax({
        //Get API with input
        url: "https://api.weather.gov/points/" + input
    //response
    }).then(function(data) {
        //change inner HTML of class locaction-name from index.html with CITY, STATE from Response
        $('.location-name').html(data.properties.relativeLocation.properties.city + ', ' + data.properties.relativeLocation.properties.state);  
        //Get 
        $.ajax({
            //Get new API from Response
            url: data.properties.forecast
        //Response
        }).then(function(data) {
            //change inner HTML of class curent-temp from index.html with detailed forecast from Response
            $('.current-temp').html(data.properties.periods[0].detailedForecast);
        });
    });
});
