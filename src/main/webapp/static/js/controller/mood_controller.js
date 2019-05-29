(function(){

	'use strict';

	angular.module('myApp').controller('MoodController',MoodController); 
	MoodController.$inject = ['MoodService','UserService'];
	function MoodController (MoodService, UserService) {
	    var self = this;
	    self.mood={id:null,moodRange:null,description:'', ts:null, uId: null};
	    self.moods=[];
	
	    self.submit = submit;
	    self.edit = edit;
	    self.remove = remove;
	    self.reset = reset;
	    self.fetchMoodsByUid = fetchMoodsByUid;
	
	
	    
	
	    function fetchMoodsByUid(id){
	        MoodService.fetchAllMoods(id)
	            .then(
	            function(d) {
	                self.moods = d.data;
	            },
	            function(errResponse){
	                console.error(errResponse);
	            }
	        );
	    } 
	    
	    function submit() {
	        if(self.mood.id===null){
	            console.log('Saving New Mood', self.mood);
	            createMood(self.mood);
	        }else{
	            updateMood(self.mood, self.mood.id);
	            console.log('Mood updated with id ', self.mood.id);
	        }
	        reset();
	    }
	
	    function createMood(mood){
	        MoodService.createMood(mood)
	            .then(
	            fetchMoodsByUid(),
	            function(errResponse){
	                console.error('Error while creating Mood');
	            }
	        );
	    }
	
	    function updateMood(mood, id, uid){
	        MoodService.updateMood(mood, id)
	            .then(
	            fetchMoodsByUid(uid),
	            function(errResponse){
	                console.error('Error while updating Mood');
	            }
	        );
	    }
	
	    function deleteMood(id,uid){
	        MoodService.deleteMood(id)
	            .then(
	            fetchMoodsByUid(uid),
	            function(errResponse){
	                console.error('Error while deleting Mood');
	            }
	        );
	    }
	
	    function submit() {
	        if(self.mood.id===null){
	            console.log('Saving New Mood', self.mood);
	            createMood(self.mood);
	        }else{
	            updateMood(self.mood, self.mood.id);
	            console.log('Mood updated with id ', self.mood.id);
	        }
	        reset();
	    }
	
	    function edit(id){
	        console.log('id to be edited', id);
	        for(var i = 0; i < self.moods.length; i++){
	            if(self.moods[i].id === id) {
	                self.mood = angular.copy(self.moods[i]);
	                break;
	            }
	        }
	    }
	
	    function remove(id,uid){
	        console.log('id to be deleted', id);
	        if(self.mood.id === id) {//clean form if the mood to be deleted is shown there.
	            reset();
	        }
	        deleteMood(id,uid);
	    }
	
	
	    function reset(){
	    	self.mood={id:null,moodRange:null,description:'', ts:null, uId: null};
	        myForm.$setPristine(); //reset Form
	    } 
	}
})();
		