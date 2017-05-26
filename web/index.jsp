<%-- 
    Document   : index
    Created on : 24 May, 2017, 11:08:57 AM
    Author     : Akhilesh yadav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>

function handle(e){
        if(e.keyCode === 13){
            e.preventDefault(); // Ensure it is only this code that rusn
			var username = document.getElementById("username").value;
			var userpass = document.getElementById("userpass").value;
                        var userrole = document.getElementById("userrole").value;
			var userquery = document.getElementById("userquery").value;
			//alert("username :: "+username+" userpass :: "+userpass+" userrole :: "+userrole+" userquery :: "+userquery);
			// make an empty object
			var jsonObject = {};
			var dataMap = {};
			var project = {};
			jsonObject.user = username;
			jsonObject.wType = "query";
//			jsonObject.userrole = userrole;
			jsonObject.queryString = userquery;
			project.type = "string";
			project.stringValue = "ProjectName";
			dataMap.project_id = project;
                        jsonObject.dataMap = dataMap;
                        var userdata = JSON.stringify(jsonObject);
			alert("jsonObject ::::  "+JSON.stringify(jsonObject));
                       // alert("jsonObject ::::  "+JSON.stringify(jsonObject));
			$.ajax({
			type:"POST",
			url: "run/skill", 
			data:{
			userdata:userdata,
			},
			success: function(result){
                            alert("result "+result);
                      //  $("#div1").html(result);
			}
		});
        }
    }

</script>
</head>
<body>

<div id="div1">
<input type="text" name="username" id="username" placeholder="enter username" /><br><br>
<input type="password" name="userpass" id="userpass" placeholder="enter password" /><br><br>
<select style="width: 13%;" id="userrole">
<option value="Manager">Manager</option>
<option value="Sales">Sales</option>
<option value="Engg">Engg</option>
</select><br><br>
<textarea id="response" style="width: 12%;display:none"></textarea><br><br>
<input type="text" name="userquery" id="userquery" placeholder="enter a query" onkeypress="handle(event)"/>
</div>
<div id="logout"> 
    <form action="run/skill" method="get">
      <input type="submit" value="Logout" />
     </form>
           
</div>


</body>
</html>
