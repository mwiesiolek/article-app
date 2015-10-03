/**
 * Created by max on 03.10.15.
 */

app.filter('authors', function(){
    return function(json){
        var converted = '';

        for(var i = 0; i < json.length; i++) {
            converted += json[i].signature + ', ';
        }

        return converted
    }
});

app.filter('keywords', function(){
    return function(json){
        var converted = '';

        for(var i = 0; i < json.length; i++) {
            converted += json[i].word + ', ';
        }

        return converted
    }
});
