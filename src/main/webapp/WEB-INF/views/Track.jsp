<%@ include file = "header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   

<body ng-app="myApp">
	<div ng-controller="UserController as ctrl" class="container">
		<div ng-controller="MoodController as mctrl">
			<div class="dropdown">
				<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
					Users
				</button>
				<div class="dropdown-menu">
					<div ng-repeat="u in ctrl.users" ng-click="mctrl.fetchMoodsByUid(u.id)">
						<p ng-bind="u.username" class="dropdown-item"></p>
					</div>
				</div>	
			</div>
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead"></span></div>
              <div class="formcontainer">
                  <form ng-submit="mctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="mctrl.mood.id" />
                      <input type="hidden" ng-model="mctrl.mood.uId"/>
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">MoodRange</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="mctrl.mood.moodRange" name="range" class="range form-control input-sm" placeholder="Enter mood rating" required ng-minlength="3"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.uname.$error.required">This is a required field</span>
                                      <span ng-show="myForm.uname.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.uname.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Description</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="mctrl.mood.description" name="description" class="description form-control input-sm" placeholder="Enter description" required/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.description.$error.required">This is a required field</span>
                                      <span ng-show="myForm.description.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!mctrl.mood.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="mctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Moods </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>ID.</th>
                              <th>MoodRange</th>
                              <th>Description</th>
                              <th>TimeStamp</th>
                              <th>User Id</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="m in mctrl.moods">
                              <td><span ng-bind="m.id"></span></td>
                              <td><span ng-bind="m.moodRange"></span></td>
                              <td><span ng-bind="m.description"></span></td>
                              <td><span ng-bind="m.ts"></span></td>
                              <td><span ng-bind="m.uId"></span></td>
                              <td>
                              <button type="button" ng-click="mctrl.edit(m.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="mctrl.remove(m.id, m.uId)" class="btn btn-danger custom-width">Remove</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
	</div>


<%@ include file = "footer.jsp" %>