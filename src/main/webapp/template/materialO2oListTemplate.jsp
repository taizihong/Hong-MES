<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<script id="materialListTemplate" type="x-tmpl-mustache">
{{#materialList}}
 <tr role="row" class="material-name odd" data-id="{{id}}"><!--even -->
	<td><input name="checkbox" type="checkbox" class="batchStart-check"/></td>
	<td>{{materialo2oId}}</td>
	<td>{{mesPlan.planOrderid}}</td>
	<td>{{materialo2oMaterialname}}</td>
	<td>{{materialo2oMaterialsource}}</td>
	<td>{{materialo2oImgid}}</td>
	<td>{{#bold}}{{showStatus}}{{/bold}}</td> 
	<td>{{materialo2oRemark}}</td>
	<td>
		<div class="hidden-sm hidden-xs action-buttons">
			 <a class="blue material-edit" href="#" data-id="{{id}}">
				  <i class="ace-icon fa fa-pencil bigger-100"></i>
			</a>
		</div>
	</td>
</tr>
{{/materialList}}
</script>