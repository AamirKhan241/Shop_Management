<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<c:import url="Header.jsp"></c:import>
<style>
    .jumbotron {
        background: #358CCE;
        color: #FFF;
        border-radius: 0px;
    }
    .jumbotron-sm { padding-top: 24px;
        padding-bottom: 24px; }
        .jumbotron small {
        color: #FFF;
    }
    .h1 small {
        font-size: 24px;
    }
</style>
<!------ Include the above in your HEAD tag ---------->

<div class="jumbotron jumbotron-sm">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 col-lg-12">
                <h1 class="h1">
                    Contact Us <small>Feel free to contact us</small></h1>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <div class="well well-sm">
                <form action="UserQuery" method="post">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="name">
                                Name</label>
                            <input type="text" class="form-control" id="name" name="Qname" placeholder="Enter name" required="required" pattern=".{3,30}" title="length should be between 3-30."/>
                        </div>
                        <div class="form-group">
                            <label for="email">
                                Email Address</label>
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span>
                                </span>
                                <input type="email" class="form-control" id="email" name="Qemail" placeholder="Enter email" required="required" /></div>
                        </div>
                        <div class="form-group">
                            <label for="subject">
                                Subject</label>
                            <input type="text" id="subject" name="Qsubject" class="form-control" placeholder="Subject"required="required" pattern=".{3,50}" title="length should be between 3-50.">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="name">
                                Message</label>
                            <textarea name="Qmessage" id="message" class="form-control" rows="9" cols="25" maxlength="500" required="required" pattern=".{3,500}" title="length should be between 3-500."
                                placeholder="Message"></textarea>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <button type="submit" class="btn btn-primary pull-right" id="btnContactUs">
                            Send Message</button>
                    </div>
                </div>
                </form>
            </div>
        </div>
        <div class="col-md-4">
            <form>
            <legend><span class="glyphicon glyphicon-globe"></span> Our office</legend>
            <address>
                <strong>BooksVilla</strong><br>
                795 Sector-24, Noida<br>
                Uttar Pradesh, India<br>
                <abbr title="Phone">
                    Phone:</abbr>
                (+91) 783-838-8335<span class="glyphicon glyphicon-phone" style="font: 20px"></span>
            </address>
            <address>
                <strong>Aamir Khan</strong><br>
                <a href="mailto:#">bookordered@gmail.com</a>
            </address>
            </form>
        </div>
    </div>
</div>
