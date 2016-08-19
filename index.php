<html>
 <head>
  <title>Risk Factors</title>
 </head>
 <body>
 <?php echo '<p>Risk Factors</p>'; 


$dbc = @mysqli_connect('localhost', 'root', 'password', 'riskfactors') OR die('could not connect to mysql: ' . mysqli_connect_error());

$query = "SELECT vw.user_id, vw.inbox_valid_word_percent, vw.outbox_valid_word_percent, sc.shared_phone_numbers FROM valid_words_tbl vw JOIN shared_contact_tbl sc ON vw.user_id = sc.user_id ORDER BY user_id";

$response = @mysqli_query($dbc, $query);

if($response){
	echo "<table border='1',style='width:100%'>
	<tr>
	<th align='left'>User</th>
	<th>Valid Words in Inbox</th>
	<th>Valid Words in Outbox</th>
	<th>Phone Numbers Shared With Other Users</th>	
	</tr>";

	while($row = mysqli_fetch_array($response)){
		echo "<tr>";
		echo "<td>" .$row['user_id']."</td>";
		echo "<td align='center'>".$row['inbox_valid_word_percent']."%</td>";
		echo "<td align='center'>".$row['outbox_valid_word_percent']."%</td>";
		echo "<td align='center'>".$row['shared_phone_numbers']."</td>";
		echo "</tr>";
	}
	echo '</table>';
}
else {
	echo "couldn't issue db query<br />";
	echo mysqli_error($dbc);
}

mysqli_close($dbc);

?> 
 </body>
</html>
