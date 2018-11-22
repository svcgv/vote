<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cm" uri="http://www.custom.com/01"%>

<tr class="trTmpl">
    <td>
    	<div class="layui-inline" style="min-width:250px;">
    		
    		<c:if test="${sapCode =='' }">
	    		<div class="layui-input-inline">
	     			<input type="text" name="custName" placeholder="请输入新客户"  value="${custName }" class="layui-input form-control" />
    			    <input type="hidden" name="sapCode" value=""/>
	    		</div>
			</c:if>
			<c:if test="${sapCode !='' }">
	    		<div class="layui-input-inline">
				    <label>${custName }</label>
				    <input type="hidden" name="sapCode" value="${sapCode }"/>
				    <input type="hidden" name="custName" value="${custName }"/>
			    </div>
			</c:if>
    	</div>
    	<div>
    		<button type="button"  class="layui-btn layui-btn-sm addProjectQuery-hook" style="vertical-align: top;;margin-top:10px;"><i class="layui-icon"></i>新增项目</button>
    		<button type="button" custId="${custId }" class="layui-btn layui-btn-sm deleteCustomer-hook" style="vertical-align: top;;margin-top:10px;;background-color: #FF5722;"><i class="layui-icon layui-icon-close"></i>删除客户</button>
    	</div>
    </td>
    <td>
    	<div class="project-list">
	     	<c:if test="${custId =='' }">
	    		<div class="layui-input-inline item"  style="margin:5px 0;">
	    			<input type="hidden" name="wbs" value=""/>
	    		</div>
	    		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
	    			<input type="hidden" name="wbs" value=""/>
	    		</div>
			</c:if>
	     	<c:if test="${custId !='' }">
	    		<div class="layui-input-inline item"  style="margin:5px 0;">
				    <div class="layui-input-inline">
			      		<input type="text" name="wbs" readonly="readonly"  class="layui-input form-control disabledColor" />
	      			</div>
	      			<button type="button"  class="layui-btn layui-btn-sm WBSQuery-hook"  style="margin-right:15px;"><i class="layui-icon layui-icon-search "></i></button>
			    </div>
	    		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
				   <div class="layui-input-inline">
			      		<input type="text" name="wbs" readonly="readonly"  class="layui-input form-control disabledColor" />
	      			</div>
	      			<button type="button"  class="layui-btn layui-btn-sm WBSQuery-hook"  style="margin-right:15px;"><i class="layui-icon layui-icon-search "></i></button>
			    </div>
			</c:if>
    	</div>
    </td>
    <td>
    	<div class="project-list">
    		<div class="layui-input-inline item" style="margin:5px 0;">
	      		<c:if test="${custId !='' }">
      			<div class="layui-input-inline">
      				<input type="text" name="projectName" readonly="readonly" class="layui-input form-control disabledColor"/>
      			</div>
	      			<button type="button"  class="layui-btn layui-btn-sm projectNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search "></i></button>
	      		</c:if>
	      		<c:if test="${custId =='' }">
	      		<div class="layui-input-inline">
      				<input type="text" name="projectName"  class="layui-input form-control"/>
      			</div>
	      		</c:if>
      		</div>
      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
      			<c:if test="${custId !='' }">
      			<div class="layui-input-inline">
      				<input type="text" name="projectName" readonly="readonly" class="layui-input form-control disabledColor"/>
      			</div>
	      			<button type="button"  class="layui-btn layui-btn-sm projectNameQuery-hook" style="margin-right:15px;"><i class="layui-icon layui-icon-search "></i></button>
	      		</c:if>
	      		<c:if test="${custId =='' }">
	      		<div class="layui-input-inline">
      				<input type="text" name="projectName"  class="layui-input form-control"/>
      			</div>
	      		</c:if>
      		</div>
    	</div>
    </td>
    <td>
    	<div class="project-list">
    		<div class="layui-input-inline item" style="margin:5px 0;">
    			 <div class="layui-input-inline">
			          <select name="projectType"  lay-filter="projectType-filter" class="form-control">
			        	<option value="">请选择</option>
			        	<option value="01" >项目</option>
			        	<option value="02" selected>产品</option>
			        	<option value="03" >人力</option>
					  </select>
      			</div>
    		</div>
    		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
    			 <div class="layui-input-inline">
		          <select name="projectType"  lay-filter="projectType-filter" class="form-control">
		        	<option value="">请选择</option>
		        	<option value="01">项目</option>
		        	<option value="02" selected>产品</option>
		        	<option value="03" >人力</option>
				  </select>
      			</div>
    		</div>
    	</div>
    </td>
    <td>
    	<div class="project-list">
    		<div class="layui-input-inline productItem">
    			<button type="button"  class="layui-btn layui-btn-sm productQuery-hook" style="vertical-align: top;"><i class="layui-icon layui-icon-search "></i></button>
      			<c:if test="${projectType == '02' }">
	      			<div class="layui-input-inline">
		      			<input name="productList" type="text" class="layui-input form-control">
	      			</div>
      			</c:if>
      			
      			<c:if test="${projectType != '02' }">
      				<div class="layui-input-inline"  style="display:none;">
	      				<input name="productList" type="text" class="layui-input form-control">
	      			</div>
     			   </c:if>
    		</div>
    		<div class="layui-input-inline productItem copyAddItem">
	      			<button type="button"  class="layui-btn layui-btn-sm productQuery-hook" style="vertical-align: top;"><i class="layui-icon layui-icon-search "></i></button>
	      			<c:if test="${projectType == '02' }">
		      			<div class="layui-input-inline">
			      			<input name="productList" type="text" class="layui-input form-control">
		      			</div>
	      			</c:if>
	      			
	      			<c:if test="${projectType != '02' }">
	      				<div class="layui-input-inline"  style="display:none;">
		      				<input name="productList" type="text" class="layui-input form-control">
		      			</div>
	      			</c:if>
    		</div>
    	</div>
    </td>
     <td>
      	<div class="project-list">
      		<div class="layui-input-inline item" style="margin:5px 0;">
      			 <div class="layui-input-inline">
			          <span class="form-control j-budgetYear">${budgetYear}</span>
			          <input type="hidden" name="budgetYear" value="${budgetYear}"/>
		      	</div>
      		</div>
      		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
      			 <div class="layui-input-inline">
			          <span class="form-control j-budgetYear">${budgetYear}</span>
			          <input type="hidden" name="budgetYear" value="${budgetYear}"/>
		      	</div>
      		</div>
      	</div>
    </td>
    <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="revenueSource" class="layui-input form-control" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="revenueSource" class="layui-input form-control" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="entity" class="layui-input form-control" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="entity" class="layui-input form-control" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="contractCode" class="layui-input form-control" />
	      		 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="contractCode" class="layui-input form-control" />
	      		 </div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="poSow" class="layui-input form-control" />
	      		 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="poSow" class="layui-input form-control" />
	      		 </div>
     		</div>
     	</div>
     </td>
      <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="custManager" class="layui-input form-control" />
	      		 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="custManager" class="layui-input form-control" />
	      		 </div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
			     	<select name="taxType"  lay-filter="" class="form-control">
			        	<option value="">请选择</option>
			        	<option value="01" selected>A</option>
			        	<option value="02">B</option>
			        	<option value="03" >C</option>
			        	<option value="04" >D</option>
			        	<option value="05" >E</option>
			        	<option value="06" >F</option>
			        	<option value="07" >G</option>
			        	<option value="08" >H</option>
			        	<option value="09" >I</option>
				  </select>
	      		 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
			     	<select name="taxType"  lay-filter="" class="form-control">
			        	<option value="">请选择</option>
			        	<option value="01" selected>A</option>
			        	<option value="02">B</option>
			        	<option value="03" >C</option>
			        	<option value="04" >D</option>
			        	<option value="05" >E</option>
			        	<option value="06" >F</option>
			        	<option value="07" >G</option>
			        	<option value="08" >H</option>
			        	<option value="09" >I</option>
				  </select>
	      		 </div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
			     	<select name="revRecognitionMethod"  lay-filter="" class="form-control">
			        	<option value="">请选择</option>
			        	<option value="01" selected>T&M</option>
			        	<option value="02">FA</option>
			        	<option value="03" >Others</option>
				   </select>
		      	 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
			     	<select name="revRecognitionMethod"  lay-filter="" class="form-control">
			        	<option value="">请选择</option>
			        	<option value="01" selected>T&M</option>
			        	<option value="02">FA</option>
			        	<option value="03" >Others</option>
				   </select>
		      	 </div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="region" class="layui-input form-control" />
	      		 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="region" class="layui-input form-control" />
	      		 </div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
			     	<select name="currency" lay-filter="" class="form-control">
			        	<option value="">请选择</option>
			        	<option value="01" selected>人民币</option>
			        	<option value="02">美元</option>
			        	<option value="03">欧元</option>
			        	<option value="04">英镑</option>
			        	<option value="05">日元</option>
				   </select>
		      	 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
			     	<select name="currency"  lay-filter="" class="form-control">
			        	<option value="">请选择</option>
			        	<option value="01" selected>人民币</option>
			        	<option value="02">美元</option>
			        	<option value="03">欧元</option>
			        	<option value="04">英镑</option>
			        	<option value="05">日元</option>
				   </select>
		      	 </div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="taxRate" class="layui-input form-control" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="taxRate" class="layui-input form-control" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="grossProfitRate" class="layui-input form-control" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="grossProfitRate" class="layui-input form-control" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list contractMoney">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
     			 	<input type="hidden"  name="contractMoney" value="" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
     			 	<input type="hidden"  name="contractMoney" value="" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list manyYearRev">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
     			 	<input type="hidden"  name="manyYearRev" value="" />
	      		 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
     			 	<input type="hidden"  name="manyYearRev" value="" />
	      		 </div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list curYearRev">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
     			 	<input type="hidden"  name="curYearRev" value="" />
	      		 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
     			 	<input type="hidden"  name="curYearRev" value="" />
	      		 </div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list lastRev">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
     			 	<input type="hidden"  name="lastRev" value="" />
	      		 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
     			 	<input type="hidden"  name="lastRev" value="" />
	      		 </div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="afterTax" class="layui-input form-control" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="afterTax" class="layui-input form-control" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <!-- 12 revenue -->
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="jan" class="layui-input form-control" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="jan" class="layui-input form-control" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="feb" class="layui-input form-control" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="feb" class="layui-input form-control" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="mar" class="layui-input form-control" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="mar" class="layui-input form-control" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="apr" class="layui-input form-control" />
	      		 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="apr" class="layui-input form-control" />
	      		 </div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="may" class="layui-input form-control" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="may" class="layui-input form-control" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="jun" class="layui-input form-control" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="jun" class="layui-input form-control" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="jul" class="layui-input form-control" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="jul" class="layui-input form-control" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="aug" class="layui-input form-control" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="aug" class="layui-input form-control" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="sep" class="layui-input form-control" />
	      		 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="sep" class="layui-input form-control" />
	      		 </div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="oct" class="layui-input form-control" />
	      		 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="oct" class="layui-input form-control" />
	      		 </div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="nov" class="layui-input form-control" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="nov" class="layui-input form-control" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="dec" class="layui-input form-control" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px 0;">
     			 <div class="layui-input-inline">
		     		<input type="text" name="dec" class="layui-input form-control" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     
     <td>
     	<div class="project-list2" style="width:90px;">
     		<div class="layui-input-inline item" style="margin:5px;">
		      <button type="button"  class="layui-btn layui-btn-sm newProjectDelete-hook" style="vertical-align: top;background-color: #FF5722;"><i class="layui-icon layui-icon-close"></i>删除</button>
     		</div>
     		<div class="layui-input-inline item copyAddItem" style="margin:5px;">
		      <button type="button"  class="layui-btn layui-btn-sm newProjectDelete-hook" style="vertical-align: top;background-color: #FF5722;"><i class="layui-icon layui-icon-close"></i>删除</button>
     		</div>
     	</div>
     </td>
</tr>