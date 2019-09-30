<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
</head>
<body>

<h1 style="text-align: center;">Soccer Predictions</h1>
<h4 style="text-align: center;">Welcome to Soccer Predictions Application!</h4>
<p>&nbsp;</p>
<p>Next matches:</p>
<table class="todayTable" style="width: 100%;">
<th>${dateCol}</th>
<th>${countryCol}</th>
<th>${leagueCol}</th>
<th>${homeTeamCol}</th>
<th>${awayTeamCol}</th>
<th>${resultCol}</th>
<th>${hdaHomeCol}</th>
<th>${hdaDrawCol}</th>
<th>${hdaAwayCol}</th>
<th>${U2_5ol}</th>
<th>${O2_5Col}</th>
<th>${GoalCol}</th>
<th>${noGoalCol}</th>

		<c:forEach var="match" items="${matches}" varStatus="status">
			<tr>
				<td>${match.date}</td>
				<td>${match.countryName}</td>
				<td>${match.leagueName}</td>
				<td>${match.homeTeamName}</td>
				<td>${match.awayTeamName}</td>
				<td>${match.result}</td>
				<td>${match.hdaHome}</td>
				<td>${match.hdaDraw}</td>
				<td>${match.hdaAway}</td>
				<td>${match.u2_5}</td>
				<td>${match.o2_5}</td>
				<td>${match.bttsYes}</td>
				<td>${match.bttsNo}</td>
			</tr>
		</c:forEach>

</table>
</body>
</html>