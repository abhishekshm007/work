<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="utf-8">

	<input type="hidden" id="sid" value=<c:out value="${user.id}"></c:out>>
	<div class="col-md-12">
		<div class="box box-primary">
			<div class="box-header">
				<h4 class="box-title">Select test and view your ranking</h4>

					<div class="col-md-10">
						<div data-ng-show="!leaderboard">
					<select  name="test"  class="form-control ">
							<option disabled selected style="display: none;" value="">
							No test found
							</option>
						</select>
				</div>
				<div data-ng-show="leaderboard">
						<select  name="test" id="test" class="form-control ">
							<option disabled selected style="display: none;" value="">
							Select Test
							</option>
							<option data-ng-repeat="x in options" value="{{x.value}}">{{x.label}}</option>
						</select>
						</div>
						
						
						
						<br>
						<b id="yourrank" style="font-size: 20px; color: red; display: none;">Your Rank:<b id="rank"></b>
						</b>

					</div>
</div>
			
			<div class="box-body table-responsive" id="v" style="display: none;">
				<table id="leaderBoardTable"
					class="table table-bordered table-striped dataTable">
					<thead>
						<tr>
							<td>Student</td>
							<td>Score(%)</td>
							<td>Rank</td>
							<td>Attempt</td>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>

