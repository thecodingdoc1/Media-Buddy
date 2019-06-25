<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Media Buddy</title>
<style>

* {
  box-sizing: border-box;
}

.column {
  float: left;
  width: 50%;
  padding: 10px;
  height: 300px; 
}

.row:after {
  content: "";
  display: table;
  clear: both;
}
</style>
</head>
<body style="font-family:Arial;font-size:20px;">
	<div style="height:65px;align: center;background: #2240B7;font-family: Arial;color: white;"">
		<br><b>
		<a href="" style="font-family:garamond;font-size:34px;text-align:center;display:block;color:white;text-decoration: none;">Media Buddy</a></b>          
		<div style="height:25px;background: #2240B7;font-family: Arial;color: white;">
			<b>
			<a href="<%=request.getContextPath()%>/bookmark" style="font-size:16px;color:white;margin-left:1150px;text-decoration:none;">Browse</a>
			<a href="<%=request.getContextPath()%>/auth/logout" style="font-size:16px;color:white;margin-left:10px;text-decoration:none;">Logout</a>
			</b>
		</div> 
	</div>
	<br><br>
	    
    <c:choose>
		<c:when test="${!empty(books)}">
			<div class="column">
				<div style="font-size: 24px;color: #333333;padding: 15px 0px 0px;border-bottom: #333333 1px solid;clear: both;">Saved Books</div>
				<br><br>
				<table>
			   		<c:forEach var = "book" items="${books}">
			     		<tr>
				   			<td>
				     			<img src="${book.imageUrl}" width="175" height="200">
				     		</td>
				     		
				   			<td style="color:gray;">
				   			Title <span style= "color: #B13100;">${book.title}</span>
		   					<br><br>
					 		By <span style="color: #B13100;">${book.authors[0]}</span>
					 		<br><br>
					 		Amazon Rating: <span style="color: #B13100;">${book.amazonRating}</span>
					 		<br><br>
					 		Publication Year: <span style="color: #B13100;">${book.publicationYear}</span>
					 		<br><br>
					 		<a href = "<%= request.getContextPath()%>/bookmark/usbook?bid=${book.id}" style="font-size:18px;color:#0058A6;font-weight:bold;text-decoration:none">Unsave</a>
				   			</td>
						</tr>   
				  		<tr>
		     	    		<td>&nbsp;</td>
		  		  		</tr>
		  			</c:forEach>
				</table>
		</div>
		<div class="column">
		<div style="font-size: 24px;color: #333333;padding: 15px 0px 0px;border-bottom: #333333 1px solid;clear: both;">Saved Movies</div>
				<br><br>
				<table>
			   		<c:forEach var = "movie" items="${movies}">
			     		<tr>
				   			<td>
				     			<img src="${movie.imageUrl}" width="175" height="200">
				   			<td style="color:gray;">
				   			Title <span style= "color: #B13100;">${movie.title}</span>
		   					<br><br>
					 		Starring <span style="color: #B13100;">${movie.actor[0]}</span>
					 		<br><br>
					 		IMDB Rating: <span style="color: #B13100;">${movie.imdbRating}</span>
					 		<br><br>
					 		Release Year: <span style="color: #B13100;">${movie.releaseYear}</span>
					 		<br><br>
					 		<a href = "<%= request.getContextPath()%>/bookmark/usmovie?mid=${movie.id}" style="font-size:18px;color:#0058A6;font-weight:bold;text-decoration:none">Unsave</a>
							</td>
				   			</td>
				    
						</tr>   

				  		<tr>
		     	    		<td>&nbsp;</td>
		  		  		</tr>
		  			</c:forEach>
				</table>
			</div>
	    </c:when>
	    <c:when test="${!empty(movies)}">
	    <div class="column">
				<div style="font-size: 24px;color: #333333;padding: 15px 0px 0px;border-bottom: #333333 1px solid;clear: both;">Saved Books</div>
				<br><br>
				<table>
			   		<c:forEach var = "book" items="${books}">
			     		<tr>
				   			<td>
				     			<img src="${book.imageUrl}" width="175" height="200">
				     		</td>
				     		
				   			<td style="color:gray;">
				   			Title <span style= "color: #B13100;">${book.title}</span>
		   					<br><br>
					 		By <span style="color: #B13100;">${book.authors[0]}</span>
					 		<br><br>
					 		Amazon Rating: <span style="color: #B13100;">${book.amazonRating}</span>
					 		<br><br>
					 		Publication Year: <span style="color: #B13100;">${book.publicationYear}</span>
					 		<br><br>
					 		<a href = "<%= request.getContextPath()%>/bookmark/usbook?bid=${book.id}" style="font-size:18px;color:#0058A6;font-weight:bold;text-decoration:none">Unsave</a>
				   			</td>
						</tr>   
				  		<tr>
		     	    		<td>&nbsp;</td>
		  		  		</tr>
		  			</c:forEach>
				</table>
		</div>
		<div class="column">
		<div style="font-size: 24px;color: #333333;padding: 15px 0px 0px;border-bottom: #333333 1px solid;clear: both;">Saved Movies</div>
				<br><br>
				<table>
			   		<c:forEach var = "movie" items="${movies}">
			     		<tr>
				   			<td>
				     			<img src="${movie.imageUrl}" width="175" height="200">
				   			<td style="color:gray;">
				   			Title <span style= "color: #B13100;">${movie.title}</span>
		   					<br><br>
					 		Starring <span style="color: #B13100;">${movie.actor[0]}</span>
					 		<br><br>
					 		IMDB Rating: <span style="color: #B13100;">${movie.imdbRating}</span>
					 		<br><br>
					 		Release Year: <span style="color: #B13100;">${movie.releaseYear}</span>
					 		<br><br>
					 		<a href = "<%= request.getContextPath()%>/bookmark/usmovie?mid=${movie.id}" style="font-size:18px;color:#0058A6;font-weight:bold;text-decoration:none">Unsave</a>
							</td>
				   			</td>
				    
						</tr>   

				  		<tr>
		     	    		<td>&nbsp;</td>
		  		  		</tr>
		  			</c:forEach>
				</table>
			</div>
     	</c:when>
     <c:otherwise>
		<br><br>
       	<span style="font-size: 24px;color: #333333;margin:400px;">You haven't saved any items yet!</span>
     </c:otherwise>
    </c:choose>
 
   
</body>
</html>