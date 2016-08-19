<html>
 <head>
  <title>Risk Factors</title>
 </head>
 <body>
 <?php echo '<h1>Risk Factors</h1>';


$dbc = @mysqli_connect('localhost', 'root', 'password', 'riskfactors') OR die('could not connect to mysql: ' . mysqli_connect_error());

$query = "SELECT vw.user_id, vw.inbox_valid_word_percent, vw.outbox_valid_word_percent, vw.inbox_total_words, vw.outbox_total_words, vw.inbox_total_messages, vw.outbox_total_messages, sc.shared_phone_numbers FROM valid_words_tbl vw JOIN shared_contact_tbl sc ON vw.user_id = sc.user_id ORDER BY user_id";

$response = @mysqli_query($dbc, $query);

if($response){
	?>

	<table border="1",style="width:100%">
	<tr>
	<th align="left">User</th>
	<th>Valid Words in Inbox</th>
	<th>Inbox Total Messages</th>
	<th>Inbox Total Words</th>

	<th>Valid Words in Outbox</th>
	<th>Outbox Total Messages</th>
	<th>Outbox Total Words</th>
	<th>Phone Numbers Shared With Other Users</th>
	</tr>

	<?php
	while($row = mysqli_fetch_array($response)){
		echo "<tr>";
		echo "<td>" .$row['user_id']."</td>";
		echo "<td bgcolor = \"#e6fff2\", align=\"center\">".$row['inbox_valid_word_percent']."%</td>";
		echo "<td bgcolor = \"#e6fff2\", align=\"center\">".$row['inbox_total_messages']."</td>";
		echo "<td bgcolor = \"#e6fff2\", align=\"center\">".$row['inbox_total_words']."</td>";

		echo "<td bgcolor = \"#e6ffff\", align=\"center\">".$row['outbox_valid_word_percent']."%</td>";
		echo "<td bgcolor = \"#e6ffff\", align=\"center\">".$row['outbox_total_messages']."</td>";
		echo "<td bgcolor = \"#e6ffff\", align=\"center\">".$row['outbox_total_words']."</td>";

		echo "<td bgcolor = \"#ffffe6\", align=\"center\">".$row['shared_phone_numbers']."</td>";
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
