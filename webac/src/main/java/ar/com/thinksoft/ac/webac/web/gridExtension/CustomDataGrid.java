package ar.com.thinksoft.ac.webac.web.gridExtension;

import java.util.List;

import org.apache.wicket.model.IModel;

import com.inmethod.grid.IDataSource;
import com.inmethod.grid.IGridColumn;
import com.inmethod.grid.datagrid.DataGrid;

@SuppressWarnings("serial")
public class CustomDataGrid extends DataGrid{
	
	public CustomDataGrid(String id, IDataSource dataSource, List<IGridColumn> columns){
		super(id,dataSource,columns);
	}
	
	protected void onItemSelectionChanged(IModel item, Boolean newValue){
		super.onItemSelectionChanged(item, newValue); 
		
	}


}
