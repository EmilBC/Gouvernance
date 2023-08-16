<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Modernize Free</title>
    <link rel="shortcut icon" type="image/png" href="assets/images/logos/favicon.png" />
    <link rel="stylesheet" href="assets/css/stylegh.css" />
</head>
<div id="w">
    <h1>Simple Github API Webapp</h1>
    <p>Enter a single Github username below and click the button to display profile info via JSON.</p>
    <form id="myForm">
        <input type="text" name="ghusername" id="ghusername" placeholder="Github username...">

        <input type="submit" value="Pull USer Data" >  

    </form>
    <div id="ghapidata" class="clearfix"></div>
</div>
<script src="assets/libs/jquery/dist/jquery.min.js"></script>
<script>

    $(function () {

        $('#myForm').on('submit', function (e) {
            e.preventDefault();
            $('#ghapidata').html('<div id="loader"><img src="https://i.imgur.com/UqLN6nl.gif" alt="loading..."></div>');

            var username = $('#ghusername').val();
            var requri = 'https://api.github.com/users/' + username;
            var repouri = 'https://api.github.com/users/' + username + '/repos';
            var listcontributors = 'https://api.github.com/repos/EmilBC/Gouvernance/contributors';
            var listCommit = 'https://api.github.com/repos/EmilBC/Gouvernance/stats/contributors';
            //requestJSON(requri, function(json) {
            requestJSON(listcontributors, function (json) {
                // alert(json);
                if (json.message == "Not Found" || username == '') {
                    $('#ghapidata').html("<h2>No User Info Found</h2>");
                } else {
                   
                    for (var i = 0; i < json.length; i++) {
                        var counter = json[i];
                        alert(counter.login);
                        alert(counter.contributions);
                    }
                    // alert(JSON.stringify(json));
                }
                /* else {
                 // else we have a user and we display their info
                 var fullname   = json.name;
                 var username   = json.login;
                 var aviurl     = json.avatar_url;
                 var profileurl = json.html_url;
                 var location   = json.location;
                 var followersnum = json.followers;
                 var followingnum = json.following;
                 var reposnum     = json.public_repos;
                 
                 if(fullname == undefined) { fullname = username; }
                 
                 var outhtml = '<h2>'+fullname+' <span class="smallname">(@<a href="'+profileurl+'" target="_blank">'+username+'</a>)</span></h2>';
                 outhtml = outhtml + '<div class="ghcontent"><div class="avi"><a href="'+profileurl+'" target="_blank"><img src="'+aviurl+'" width="80" height="80" alt="'+username+'"></a></div>';
                 outhtml = outhtml + '<p>Followers: '+followersnum+' - Following: '+followingnum+'<br>Repos: '+reposnum+'</p></div>';
                 outhtml = outhtml + '<div class="repolist clearfix">';
                 
                 var repositories;
                 $.getJSON(repouri, function(json){
                 repositories = json;   
                 outputPageContent();                
                 });          
                 
                 function outputPageContent() {
                 if(repositories.length == 0) { outhtml = outhtml + '<p>No repos!</p></div>'; }
                 else {
                 outhtml = outhtml + '<p><strong>Repos List:</strong></p> <ul>';
                 $.each(repositories, function(index) {
                 outhtml = outhtml + '<li><a href="'+repositories[index].html_url+'" target="_blank">'+repositories[index].name + '</a></li>';
                 });
                 outhtml = outhtml + '</ul></div>'; 
                 }
                 $('#ghapidata').html(outhtml);
                 } // end outputPageContent()
                 } /// end else statement*/
            }); // end requestJSON Ajax call
        }); // end click event handler  

        function requestJSON(url, callback) {
            $.ajax({
                url: url,
                complete: function (xhr) {
                    callback.call(null, xhr.responseJSON);
                }
            });

        }

    });

</script>