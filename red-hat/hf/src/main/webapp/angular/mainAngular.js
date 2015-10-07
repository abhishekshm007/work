'use strict';

var sampleApp = angular.module('mainApp', ['ui.router.state']);




sampleApp.controller('mainController', function($scope, $location, $http){

	$scope.menuActive = function(page){
		var current = $location.path().substring(1);
		return page === current ? 'active' : '';
	};
	
	$scope.verifyPincode = function(){
		$http({
    		method:'POST',
    		url:'VerifyPincode',
    		headers:{'Content-Type':'application/x-www-form-urlencoded'},
    		data: "code="+$scope.pincode
    	}
    	).success(function(data, status, headers, config) {
    		notif({ 
    			msg: data,
				position:'center',
				type: "success"
			});
    	}).error(function(data, status, headers, config) {
    		notif({ 
    			msg: data,
				position:'center',
				type: "error"
    		});
    	}
    	);
	};
//	$scope.stockCotegoryList = [];
//	$scope.loadStockList = function(){
//		$http({
//    		method:'POST',
//    		url:'GetStockCotegoryList',
//    		headers:{'Content-Type':'application/x-www-form-urlencoded'},
//    	}
//    	).success(function(data, status, headers, config) {
//    		var i =0;
//    		 for (; i < data.length; i++) {
//                 $scope.stockCotegoryList.push({
//                	 stockCotegoryId: data[i].stockCotegoryId,
//                	 stockCotegoryName: data[i].stockCotegoryName,
//                	 productCotegoryList : data[i].productCotegoryList
//                 });
//             }
//    	}).error(function(data, status, headers, config) {
//    		notif({ 
//    			msg: data,
//				position:'center',
//				type: "error"
//    		});
//    	}
//    	);
//	}
//
//	$scope.loadStockList();
//	
	$scope.productList = [];
	$scope.first = 0;
	$scope.moreProductCounts = 0;
	$scope.lastTimeProductCount = 0;
	$scope.loadProductList = function(){
		$http({
    		method:'POST',
    		url:'GetProductListForHome',
    		headers:{'Content-Type':'application/x-www-form-urlencoded'},
    		data: "first="+$scope.first
    	}
    	).success(function(data, status, headers, config) {
    		try{
    		var i =0;
    		 for (; i < data.length; i++) {
                 $scope.productList.push({
                     productId: data[i].productId,
                     productName: data[i].productName,
                     productDesc: data[i].productDesc,
                     productAmount: data[i].productAmount,
                     isNew: (data[i].isNew == true ? 1 : 0),
                     isSale: (data[i].isSale == true ? 1 : 0)
                 });
             }
    		 $scope.lastTimeProductCount = data.length;
    		 $http({
    	    		method:'POST',
    	    		url:'GetProductListCountForHome',
    	    		headers:{'Content-Type':'application/x-www-form-urlencoded'},
    	    		data: "first="+$scope.first
    	    	}
    	    	).success(function(adata, status, headers, config) {
    	    		try{
    	    		 $scope.moreProductCounts = adata -$scope.first - $scope.lastTimeProductCount;
    	    		 $scope.first = data.length + $scope.first;
    	    		 }
    	    		catch(err){
    	    			alert("error "+err);
    	    		}
    	    	}).error(function(data, status, headers, config) {
    	    		notif({ 
    	    			msg: data,
    					position:'center',
    					type: "error"
    	    		});
    	    	}
    	    	);
    		 }
    		catch(err){
    			alert("error "+err);
    		}
    	}).error(function(data, status, headers, config) {
    		notif({ 
    			msg: data,
				position:'center',
				type: "error"
    		});
    	}
    	);
	}
	
	$scope.loadProductList();
});

