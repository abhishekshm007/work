<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Student -->
<div class="row">

	<div class="col-md-12  toppad">


		<div class="box box-primary" id="box1">

			<div class="box-body">
				<div class="row">
					<div class="col-md-2">
						<a data-toggle="modal" class="fakeLink" title="click to change" data-target="#editdp" id="dp">
							<img src="../ProfilePicture?id=${user.id}" class="img-circle"
							alt="User Image" style="height: 180px; width: 180px;" />Edit
						</a>
					</div>
					<div class=" col-md-10">
						<table class="table table-user-information table-responsive">
							<tbody>
								<tr>
									<td style="width: 150px;">Name :</td>
									<td id="name">{{name}}</td>
									<td><a data-toggle="modal" class="fakeLink"
										data-target="#editprofile" onclick="editUserProfile()">Edit</a></td>
								</tr>
								<tr>
									<td>Email :</td>
									<td id="email">{{email}}</td>
									<td><a data-toggle="modal" class="fakeLink"
										data-target="#editprofile" onclick="editUserProfile()">Edit</a></td>
								</tr>
								<tr>
									<td>Batch :</td>
									<td id="batch">{{batch}}</td>
									<td></td>
								</tr>
								<tr>
									<td>Gender :</td>
									<td id="gender">{{gender}}</td>
									<td><a data-toggle="modal" class="fakeLink"
										data-target="#editprofile" onclick="editUserProfile()">Edit</a></td>
								</tr>
								<tr>
									<td>Date of birth :</td>
									<td id="dob">{{dob}}</td>
									<td><a data-toggle="modal" class="fakeLink"
										data-target="#editprofile" onclick="editUserProfile()">Edit</a></td>
								</tr>
								<tr>
									<td>Contact no. :</td>
									<td id="contact">{{contact}}</td>
									<td><a data-toggle="modal" class="fakeLink"
										data-target="#editprofile" onclick="editUserProfile()">Edit</a></td>
								</tr>
								<tr>
									<td>Address :</td>
									<td id="address">{{address}}</td>
									<td><a data-toggle="modal" class="fakeLink"
										data-target="#editprofile" onclick="editUserProfile()">Edit</a></td>
								</tr>
								<tr>
									<td>Password :</td>
									<td>********</td>
									<td><a data-toggle="modal" class="fakeLink" data-target="#editpassword">Edit</a></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>


		</div>
	</div>
</div>
<div id="editprofile" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div id="myModal1" class="modal-dialog modal-lg">
		<div class="modal-content" id="box">
			<div class="modal-header" id="modalheader">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" onclick="clear()">×</button>
				<h3 class="modal-title" class="label label-success">Edit
					Profile</h3>
			</div>

			<form id="userProfileForm">
				<div class="modal-body">
					<div class="form-group">
						<label for="name">Name</label> <input id="n" required name="name"
							type="text" placeholder="Name" class="form-control input-md">
					</div>

					<div class="form-group">
						<label for="email">Email</label> <input id="e" required
							name="email" type="email" placeholder="Email address"
							class="form-control input-md">
					</div>
					<div class="form-group">
						<label for="Gender">Gender</label> 
						<select id="g" name="gender"class="form-control input-md" >
						<option value="" style="display: none;">Select gender</option>
						<option value="M" >Male</option>
						<option value="F" >Female</option>
						
						</select>
					</div>
					<div class="form-group">
						<label for="dob">Date of birth</label> <input id="d" 
							name="dob" type="date" placeholder="Date of birth"
							class="form-control input-md">
					</div>
					<div class="form-group">
						<label for="contact">Contact number </label><input id="c"
							name="contact" type="number" placeholder="Contact number"
							class="form-control input-md">
					</div>
					<div class="form-group">
						<label for="address">Address </label>
						<textarea name="address" id="a" placeholder="Address"
							class="form-control input-md"></textarea>
					</div>
				</div>

				<div class="modal-footer">
						<button type="submit" class="btn btn-primary">Save changes</button>
				<button href="#" data-dismiss="modal" class="btn btn-default">Close</button>
				
				</div>
			</form>
		</div>
	</div>
</div>

<div id="editpassword" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div id="myModal1" class="modal-dialog modal-lg">
		<div class="modal-content" id="box">
			<div class="modal-header" id="modalheader">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" onclick="clear()">×</button>
				<h3 class="modal-title" class="label label-success">Enter new Password</h3>
			</div>

			<form id="passwordForm" method="post">
				<div class="modal-body">
					<div class="form-group">
						<input name="p" required  minlength="6"
							type="password" placeholder="New Password"
							class="form-control input-md">
					</div>
				</div>
				<div class="modal-footer">
					<button href="#" data-dismiss="modal" class="btn btn-default">Close</button>
					<button type="submit" margin class="btn btn-primary">Save</button>
				</div>
			</form>
		</div>
	</div>
</div>

<div id="editdp" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div id="myModal1" class="modal-dialog modal-lg">
		<div class="modal-content" id="box5">
			<div class="modal-header" id="modalheader">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" onclick="clear()">×</button>
				<h3 class="modal-title" class="label label-success">New profile picture</h3>
			</div>
			<form enctype="multipart/form-data" onsubmit="uploadDp(this,'../UploadDp')" id="dpForm" method="post">
				<div class="modal-body">
					<div class="form-group">
						<input name="dp" required accept=".png,.jpg,.jpeg"
							type="file" placeholder="New dp"
							class="form-control input-md">
							<span class="help-block">
										Note:- Only jpg,png or jpeg format is alowed.</span>
					</div>
				</div>
				<div class="modal-footer">
					<button href="#" data-dismiss="modal" class="btn btn-default">Close</button>
					<button type="submit" margin class="btn btn-primary">Save</button>
				</div>
			</form>
		</div>
	</div>
</div>

