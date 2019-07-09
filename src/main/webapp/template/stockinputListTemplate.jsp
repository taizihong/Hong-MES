<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<script id="orderListTemplate" type="x-tmpl-mustache">
{{#orderList}}
 <tr role="row" class="order-name odd" data-id="{{id}}"><!--even -->
	<td><input name="checkbox" type="checkbox" class="batchStart-check"/></td>
	<td>{{mesStorage.storageName}}</td>
	<td>{{stockMid}}</td>
	<td>{{stockMaterialname}}</td>
	<td>{{stockImgid}}</td>
	<td>{{stockMaterialname}}</td>
	<td>{{stockMaterialsource}}</td>
	<td>{{stockType}}</td>
	<td>{{stockTargetweight}}</td>
	<td>{{stockLeftweight}}</td>
	<td>{{stockIrontypeweight}}</td>
	<td>{{stockCheck}}</td>
	<td>{{stockRemark}}</td>
	<td>{{#come_date}}{{/come_date}}</td>
	<td>{{#commit_date}}{{/commit_date}}</td>
	<td>
		<div class="hidden-sm hidden-xs action-buttons">
			 <a class="blue order-edit" href="#" data-id="{{id}}">
				  <i class="ace-icon fa fa-pencil bigger-100"></i>
			</a>
		</div>
	</td>
</tr>
{{/orderList}}
</script>