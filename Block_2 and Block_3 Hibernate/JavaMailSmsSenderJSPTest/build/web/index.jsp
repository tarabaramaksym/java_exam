<%@page import="JavaMail.JavaMailServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mail</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="row" style="margin:45px">
            <div class="col-4"></div>
            
            <form class="row col-4" method="POST">
                <div class="col-6">
                  <input class="form-control" placeholder="Input email" name="email" type="email" />
                </div>

                <div class="col-6">
                  <input class="form-control" placeholder="Input phone" name="phone" />
                </div>

                <div class="col-12">
                  <input style="margin-top:15px" class="form-control" placeholder="Input message" name="message" />
                </div>

                <div class="col-7"></div>

              <button  class="btn btn-primary col-4" type="submit" style="margin:10px">Send</button>
          </form>

          <div class="col-4"></div>
        
        </div>
       
   
    </body>
</html>
