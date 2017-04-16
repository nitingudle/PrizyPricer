$(document).ready(function() {
	startIndex = 0;
	limit = 6;
	totalSize = 0;
	$(document).ajaxStart(function() {
		$("#wait").css("display", "block");
	});
	$(document).ajaxComplete(function() {
		$("#wait").css("display", "none");
	});

	$('form')[0].reset();

	$("#addProduct").on("click", function() {
		$("#mainArea").hide(1000);
		$("#userArea").show(1000);
		setTimeout(function(){ $("#backButton").show(); }, 1000);
		
	});
	$("#showProduct").on("click", function() {
		$("#mainArea").hide(1000);
		$("#adminArea").show(1000);
		setTimeout(function(){ $("#backButton").show(); }, 1000);
		startIndex = 0;
		$("#showProduct").getPage();
		
	});
	$("#backButton").on("click", function() {
		$("#backButton").hide();
		$("#mainArea").show(1000);
		$("#userArea").hide(1000);
		$("#adminArea").hide(1000);
		$("#showProduct").setValues();
	});
	
	$("#submitProduct").on("click", function() {
		$.ajax({
            type: "post",
            url: window.location.pathname.substring(0, window.location.pathname.indexOf("/",2))+"/API/v1/products",
            dataType: "json",
            data: { store : $("#storeName").val(), productCode : $("#productCode").val(),price : $("#productPrice").val(),notes : $("#productDesc").val()},
            success: function (returnedData) {
            	$("#submitError").hide(500)
				$("#submitSuccess").show(500).html('<strong>Success !</strong>');
            },
            error: function (returnedData, errorThrown) {
            	$("#submitSuccess").hide(500)
		    	$("#submitError").show(500).html('<strong>Error !</strong> '+returnedData.responseJSON.error);	
            }
        });
	});

	$("#productTable").on('dblclick','tr',function(e){
	    e.preventDefault();
	    var id = $(this)[0];
	    var productCode=id.cells[1].innerText;
	    if(productCode!='Product Code')
	    { 
	    	$("#idealPrice").getPrice(id.cells[1].innerText);     
	    	 
	    }
	}); 
	
	
	$("#backPage").on("click", function() {
		startIndex = startIndex - limit;
		$("#showProduct").getPage();
	});
	
	$("#nextPage").on("click", function() {
		startIndex = startIndex + limit;
		$("#showProduct").getPage();
	});
	
	
	(function( $ ){
	   $.fn.getPage = function() {
		   $.ajax({
		        type: "get",
		        url: window.location.pathname.substring(0, window.location.pathname.indexOf("/",2))+"/API/v1/products",
		        dataType: "json",
		        data: { startIndex : startIndex, limit : limit},
		        success: function (returnedData) {
		        	totalSize = returnedData.size;
		        	var tbody = $('#productTable tbody');
		        	tbody.empty();
		        	$.each(returnedData.products, function(i, product) {
		        		  var tr = $('<tr>');
		        		  $.each(product, function(i, p) {
		        		    $('<td>').html(p).appendTo(tr);  
		        		  });
		        		  tbody.append(tr);
		        		});
		        	
		        	$("#listError").hide(500)
					$("#listSuccess").show(500).html('<strong>Success !</strong>');
		        	$("#manageBackNext").manageBackNext();
		        },
		        error: function (returnedData, errorThrown) {
		        	$("#listSuccess").hide(500)
			    	$("#listError").show(500).html('<strong>Error !</strong> '+returnedData.responseJSON.error);	
		        }
		    });
		   };
		   $.fn.getPrice = function(code) {
			   $.ajax({
			        type: "get",
			        url: window.location.pathname.substring(0, window.location.pathname.indexOf("/",2))+"/API/v1/products/"+code,
			        dataType: "json",
			        success: function (returnedData) {
			        	$('#idealPrice').modal('show');
				    	$("#idealPrice").on("shown.bs.modal", function () {  
				    		var tbody = $('#idealPriceTable tbody');
				    		tbody.empty();
				    		var tr = $('<tr>');
				    		$('<td>').html(returnedData.productName).appendTo(tr);
				    		$('<td>').html(returnedData.productCode).appendTo(tr);
				    		$('<td>').html(returnedData.productDesc).appendTo(tr);
				    		$('<td>').html(returnedData.averagePrice).appendTo(tr);
				    		$('<td>').html(returnedData.lowestPrice).appendTo(tr);
				    		$('<td>').html(returnedData.highestPrice).appendTo(tr);
				    		$('<td>').html(returnedData.idealPrice).appendTo(tr);
				    		$('<td>').html(returnedData.total).appendTo(tr);
				    		tbody.append(tr);
				        }).modal('show');
			        	
			        	$("#listError").hide(500)
						$("#listSuccess").show(500).html('<strong>Success !</strong>');
			        	$("#manageBackNext").manageBackNext();
			        },
			        error: function (returnedData, errorThrown) {
			        	$("#listSuccess").hide(500)
				    	$("#listError").show(500).html('<strong>Error !</strong> '+returnedData.responseJSON.error);	
			        }
			    });
			   };
		   $.fn.manageBackNext = function() {
			   
			   if(startIndex + limit < totalSize)
				   $("#nextPage").show();
			   else
				   $("#nextPage").hide();
			   if(startIndex == 0)
				   $("#backPage").hide();
			   else
				   $("#backPage").show();
			   
		   };
		   
		   $.fn.setValues = function() {
				startIndex = 0;
				limit = 6;
				totalSize = 0;   
		    };
		   
		})( jQuery );
	
});

