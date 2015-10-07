<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<footer>
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<div class="widget">
					<h5 class="widgetheading">Get in touch with us :</h5>
					<address>
					<strong><c:out value="${applicationScope.appName }"></c:out> company</strong><br>
					 </address>
					<p>
						<i class="icon-phone"></i>(+91) 8868979259<br>
						<i class="icon-phone"></i>(+91) 9761604698 <br>
						<i class="icon-envelope-alt"></i><a href="mailto:support@o9paathshala.com"> support@<c:out value="${applicationScope.appName }"></c:out>.com</a>
					
					</p>
				</div>
			</div>
			<div class="col-lg-3">
				<div class="widget">
					<h5 class="widgetheading">Pages</h5>
					<ul class="link-list">
						<li><a href="index.jsp">Home</a></li>
						<li><a href="faqs.jsp">FAQs</a></li>
						<li><a href="tnc.jsp">Terms and conditions</a></li>
						<li><a href="privacyPolicy.jsp">Privacy policy</a></li>
						<li><a href="feedback.jsp">Feedback</a></li>
						<li><a href="contactus.jsp">Contact us</a></li>
					</ul>
				</div>
			</div>
			<div class="col-lg-3">
				<div class="widget">
					<h5 class="widgetheading">Downloads</h5>
					<ul class="link-list">
						<li><a href="http://goo.gl/D9di7s">Android application</a></li>
					</ul>
				</div>
				
				<div class="widget">
					<h5 class="widgetheading">Like us :</h5>
					<ul class="link-list">
						<li><div class="fb-like" data-href="https://www.facebook.com/o9paathshala" data-layout="button_count" data-action="like" data-show-faces="true" data-share="true"></div></li>
						<li><div>
						<a href="https://twitter.com/o9paathshala" class="twitter-follow-button" data-show-count="false" data-size="" data-dnt="true">Follow @o9paathshala</a>
						<a href="https://twitter.com/share" class="twitter-share-button" data-url="http://o9paathshala.com" data-text="o9Paathshala" data-via="o9paathshala" data-size="" data-related="supporto9paathshalacom" data-hashtags="ShareItAndUseit" data-dnt="true">Tweet</a>
						
						<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');</script>
						</div></li>
						<li><div class="g-plusone" data-size="medium" data-href="https://plus.google.com/111350645629112169835">
							<!-- Place this tag after the last +1 button tag. -->
							<script type="text/javascript">
  								(function() {
    								var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
    								po.src = 'https://apis.google.com/js/platform.js';
    								var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
  								})();
							</script>
						</div>
						</li>
					</ul>
				</div>
			</div>
			<div class="col-lg-3">
				<div class="widget">
					<h5 class="widgetheading">Social Media</h5>
						<ul class="" style="list-style:none;">
							<li><a href="https://www.facebook.com/o9paathshala" target="_blank"><i class="fa fa-facebook"></i> Facebook</a></li>
							<li><a href="https://twitter.com/o9paathshala" target="_blank" title="Twitter"><i class="fa fa-twitter"></i> Twitter</a></li>
							<li><a href="https://in.pinterest.com/o9paathshala" target="_blank" title="Pinterest"><i class="fa fa-pinterest"></i> Pinterest</a></li>
							<li><a href="https://plus.google.com/111350645629112169835" target="_blank" title="Google Plus"><i class="fa fa-google-plus"></i> Google Plus</a></li>
							
						</ul>
					<div class="clear">
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="sub-footer">
		<div class="container">
			<div class="row">
				<div class="col-lg-6">
					<div class="copyright">
						<p>
							<span>&copy; <a href="http://www.o9paathshala.com"><c:out value="${applicationScope.appName }"></c:out></a> 2015 All right reserved.</span>
						</p>
					</div>
				</div>
				<div class="col-lg-6">
					<ul class="social-network">
						<li><a href="https://www.facebook.com/o9paathshala" target="_blank" data-placement="top" title="Facebook"><i class="fa fa-facebook"></i></a></li>
						<li><a href="https://twitter.com/o9paathshala" target="_blank" data-placement="top" title="Twitter"><i class="fa fa-twitter"></i></a></li>
						<li><a href="https://in.pinterest.com/o9paathshala" target="_blank" data-placement="top" title="Pinterest"><i class="fa fa-pinterest"></i></a></li>
						<li><a href="https://plus.google.com/111350645629112169835" target="_blank" data-placement="top" title="Google plus"><i class="fa fa-google-plus"></i></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	</footer>

<script src="js/jquery.js"></script>
<script src="js/notification.js"></script>
<script src="js/jquery.easing.1.3.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.fancybox.pack.js"></script>
<script src="js/jquery.fancybox-media.js"></script>
<script src="js/google-code-prettify/prettify.js"></script>
<script src="js/portfolio/jquery.quicksand.js"></script>
<script src="js/portfolio/setting.js"></script>
<script src="js/jquery.flexslider.js"></script>
<script src="js/spin.js" type="text/javascript"></script> 
<script src="js/animate.js"></script>
<script src="js/custom.js"></script>
<script src="js/angular/angular.js"></script>
<script type="text/javascript">
(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
})(window,document,'script','//www.google-analytics.com/analytics.js','ga');

ga('create', 'UA-60953599-1', 'auto');
ga('send', 'pageview');

</script>


<!-- Login ajax js -->
<script src="js/angular/homeAngular.js"></script>


</body>
</html>
