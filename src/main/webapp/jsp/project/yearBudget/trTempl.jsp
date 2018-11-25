<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cm" uri="http://www.custom.com/01"%>

<tr class="trTmpl">
    <td>
    	<div class="layui-inline" style="min-width:250px;">
    		<c:if test="${custId =='' }">
	    		<div class="layui-input-inline">
	     			<input type="text" name="custName" placeholder="请输入新客户"  value="${custName }" class="layui-input form-control" />
    			    <input type="hidden" name="sapCode" value=""/>
	    		</div>
			</c:if>
			<c:if test="${custId !='' }">
	    		<div class="layui-input-inline">
				    <label>${custName }</label>
				    <input type="hidden" name="sapCode" value="${custId }"/>
				    <input type="hidden" name="custName" value="${custName }"/>
			    </div>
			</c:if>
    	</div>
    	<div>
    		<button type="button"  class="layui-btn layui-btn-sm addProjectQuery-hook" style="vertical-align: top;"><i class="layui-icon"></i>新增项目</button>
    		<button type="button" custId="${custId }" class="layui-btn layui-btn-sm deleteCustomer-hook" style="vertical-align: top;background-color: #FF5722;"><i class="layui-icon layui-icon-close"></i>删除客户</button>
    	</div>
    </td>
    <td>
    	<div class="project-list">
	     	<c:if test="${custId =='' }">
	    		<div class="layui-input-inline item"  >
	    			<input type="hidden" name="wbs" value=""/>
	    		</div>
	    		<div class="layui-input-inline item copyAddItem" >
	    			<input type="hidden" name="wbs" value=""/>
	    		</div>
			</c:if>
	     	<c:if test="${custId !='' }">
	    		<div class="layui-input-inline item"  >
				    <div class="layui-input-inline">
			      		<input type="text" name="wbs" readonly="readonly"  class="layui-input form-control disabledColor" />
	      			</div>
	      			<button type="button"  class="layui-btn layui-btn-sm WBSQuery-hook"><i class="layui-icon layui-icon-search "></i></button>
			    </div>
	    		<div class="layui-input-inline item copyAddItem" >
				   <div class="layui-input-inline">
			      		<input type="text" name="wbs" readonly="readonly"  class="layui-input form-control disabledColor" />
	      			</div>
	      			<button type="button"  class="layui-btn layui-btn-sm WBSQuery-hook"><i class="layui-icon layui-icon-search "></i></button>
			    </div>
			</c:if>
    	</div>
    </td>
    <td>
    	<div class="project-list">
    		<div class="layui-input-inline item" >
	      		<c:if test="${custId !='' }">
      			<div class="layui-input-inline">
      				<input type="text" name="projectName"  class="layui-input form-control disabledColor"/>
      			</div>
	      			<button type="button"  class="layui-btn layui-btn-sm projectNameQuery-hook"><i class="layui-icon layui-icon-search "></i></button>
	      		</c:if>
	      		<c:if test="${custId =='' }">
	      		<div class="layui-input-inline">
      				<input type="text" name="projectName"  class="layui-input form-control"/>
      			</div>
	      		</c:if>
      		</div>
      		<div class="layui-input-inline item copyAddItem" >
      			<c:if test="${custId !='' }">
      			<div class="layui-input-inline">
      				<input type="text" name="projectName" class="layui-input form-control disabledColor"/>
      			</div>
	      			<button type="button"  class="layui-btn layui-btn-sm projectNameQuery-hook"><i class="layui-icon layui-icon-search "></i></button>
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
    		<div class="layui-input-inline item" >
    			 <div class="layui-input-inline">
			          <select name="projectType"  class="form-control">
			        	 ${projectType2.ewTypeHtml}
					  </select>
      			</div>
    		</div>
    		<div class="layui-input-inline item copyAddItem" >
    			 <div class="layui-input-inline">
		          <select name="projectType"  class="form-control">
		        	${projectType2.ewTypeHtml}
				  </select>
      			</div>
    		</div>
    	</div>
    </td>
    <td>
		<div class="project-list">
			<div class="layui-input-inline item" >
				<div class="layui-input-inline">
					<select name="isChooseProduct" lay-filter="isChooseProduct-filter" class="form-control">
						<option value="">请选择</option>
						<option value="01" selected>是</option>
						<option value="02">否</option>
					</select>
				</div>
			</div>
			<div class="layui-input-inline item copyAddItem">
				<div class="layui-input-inline">
					<select name="isChooseProduct" lay-filter="isChooseProduct-filter" class="form-control">
						<option value="">请选择</option>
						<option value="01" selected>是</option>
						<option value="02">否</option>
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
      		<div class="layui-input-inline item" >
      			 <div class="layui-input-inline">
			          <select name="budgetYear" class="j-budgetYear"></select>
		      	</div>
      		</div>
      		<div class="layui-input-inline item copyAddItem" >
      			 <div class="layui-input-inline">
			          <select name="budgetYear" class="j-budgetYear"></select>
		      	</div>
      		</div>
      	</div>
    </td>
    <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
		     		<input type="text" name="revenueSource" class="layui-input form-control" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
		     		<input type="text" name="revenueSource" class="layui-input form-control" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
		     		<input type="text" name="companyCode" class="layui-input form-control" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
		     		<input type="text" name="companyCode" class="layui-input form-control" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
		     		<input type="text" name="contractCode" class="layui-input form-control" />
	      		 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
		     		<input type="text" name="contractCode" class="layui-input form-control" />
	      		 </div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
		     		<input type="text" name="poSow" class="layui-input form-control" />
	      		 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
		     		<input type="text" name="poSow" class="layui-input form-control" />
	      		 </div>
     		</div>
     	</div>
     </td>
      <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
		     		<input type="text" name="custManager" class="layui-input form-control" />
	      		 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
		     		<input type="text" name="custManager" class="layui-input form-control" />
	      		 </div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
			     	<select name="taxType"  lay-filter="" class="form-control">
			        	${taxType.ewTypeHtml}
				  </select>
	      		 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
			     	<select name="taxType"  lay-filter="" class="form-control">
			        	${taxType.ewTypeHtml}
				  </select>
	      		 </div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
			     	<select name="revRecognitionMethod"  lay-filter="" class="form-control">
			        	${revRecognitionMethod.ewTypeHtml}
				   </select>
		      	 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
			     	<select name="revRecognitionMethod"  lay-filter="" class="form-control">
			        	${revRecognitionMethod.ewTypeHtml}
				   </select>
		      	 </div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
     			 	<select name="region"  lay-filter="" class="form-control">
			        	${region.ewTypeHtml}
				   </select>
		     		
	      		 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
		     		<select name="region"  lay-filter="" class="form-control">
			        	${region.ewTypeHtml}
				   </select>
	      		 </div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
			     	<select name="currency" lay-filter="" class="form-control">
			        	${currency.ewTypeHtml}
				   </select>
		      	 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
			     	<select name="currency"  lay-filter="" class="form-control">
			        	${currency.ewTypeHtml}
				   </select>
		      	 </div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="taxRate" class="layui-input form-control" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="taxRate" class="layui-input form-control" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="grossProfitRate" class="layui-input form-control" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="grossProfitRate" class="layui-input form-control" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list contractMoney">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
     			 	<input type="number"  name="contractMoney" value="" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
     			 	<input type="number"  name="contractMoney" value="" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list manyYearRev">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
	      		 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
	      		 </div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list curYearRev">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
	      		 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
	      		 </div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list lastRev">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
     			 	<input type="number"  name="lastRev"  class="layui-input form-control"/>
	      		 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
     			 	<input type="number"  name="lastRev"  class="layui-input form-control"/>
	      		 </div>
     		</div>
     	</div>
     </td>
     <td>
		<div class="project-list">
				<div class="layui-input-inline item" >
					<input type="number" name="budgetSum"  readonly="readonly" class="layui-input form-control disabledColor" />
				</div>
			<div class="layui-input-inline item copyAddItem" >
				<div class="layui-input-inline">
					<input type="number" name="budgetSum"  readonly="readonly" class="layui-input form-control disabledColor" />
				</div>
			</div>
		</div>
	</td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="afterTax"  readonly="readonly" class="layui-input form-control disabledColor" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="afterTax"  readonly="readonly" class="layui-input form-control disabledColor" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <!-- 12 revenue -->
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="budgetJan" class="layui-input form-control j-monthCalc" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="budgetJan" class="layui-input form-control j-monthCalc" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="budgetFeb" class="layui-input form-control j-monthCalc" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="budgetFeb" class="layui-input form-control j-monthCalc" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="budgetMar" class="layui-input form-control j-monthCalc" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="budgetMar" class="layui-input form-control j-monthCalc" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="budgetApr" class="layui-input form-control j-monthCalc" />
	      		 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="budgetApr" class="layui-input form-control j-monthCalc" />
	      		 </div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="budgetMay" class="layui-input form-control j-monthCalc" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="budgetMay" class="layui-input form-control j-monthCalc" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="budgetJun" class="layui-input form-control j-monthCalc" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="budgetJun" class="layui-input form-control j-monthCalc" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="budgetJul" class="layui-input form-control j-monthCalc" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="budgetJul" class="layui-input form-control j-monthCalc"/>
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="budgetAug" class="layui-input form-control j-monthCalc" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="budgetAug" class="layui-input form-control j-monthCalc" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="budgetSep" class="layui-input form-control j-monthCalc" />
	      		 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="budgetSep" class="layui-input form-control j-monthCalc" />
	      		 </div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="budgetOct" class="layui-input form-control j-monthCalc" />
	      		 </div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="budgetOct" class="layui-input form-control j-monthCalc" />
	      		 </div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="budgetNov" class="layui-input form-control j-monthCalc" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="budgetNov" class="layui-input form-control j-monthCalc" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     <td>
     	<div class="project-list">
     		<div class="layui-input-inline item" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="budgetDec" class="layui-input form-control j-monthCalc" />
	      	 	</div>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
     			 <div class="layui-input-inline">
		     		<input type="number" name="budgetDec" class="layui-input form-control j-monthCalc" />
	      	 	</div>
     		</div>
     	</div>
     </td>
     
     <td>
     	<div class="project-list2" style="width:90px;">
     		<div class="layui-input-inline item" >
		      <button type="button"  class="layui-btn layui-btn-sm newProjectDelete-hook" style="vertical-align: top;background-color: #FF5722;"><i class="layui-icon layui-icon-close"></i>删除</button>
     		</div>
     		<div class="layui-input-inline item copyAddItem" >
		      <button type="button"  class="layui-btn layui-btn-sm newProjectDelete-hook" style="vertical-align: top;background-color: #FF5722;"><i class="layui-icon layui-icon-close"></i>删除</button>
     		</div>
     	</div>
     </td>
</tr>