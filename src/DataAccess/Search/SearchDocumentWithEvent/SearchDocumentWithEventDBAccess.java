package DataAccess.Search.SearchDocumentWithEvent;

import DataAccess.DatabaseConnexion;
import DataAccess.Document.DocumentDBAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Document.DocumentException;
import Exceptions.Search.GetDocumentWithSpecificEventException;
import Exceptions.Search.GetQuantityItemWithSpecificEventException;
import Model.Document.Document;
import Model.Document.MakeDocument;
import Model.DocumentStatus.DocumentStatus;
import Model.DocumentStatus.MakeDocumentStatus;
import Model.Employee.Employee;
import Model.Employee.MakeEmployee;
import Model.EmployeeStatus.EmployeeStatus;
import Model.EmployeeStatus.MakeEmployeeStatus;
import Model.Process.MakeProcess;
import Model.Process.Process;
import Model.ProcessStatus.MakeProcessStatus;
import Model.ProcessStatus.ProcessStatus;
import Model.ProcessType.MakeProcessType;
import Model.ProcessType.ProcessType;
import Model.Supplier.MakeSupplier;
import Model.Supplier.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchDocumentWithEventDBAccess implements SearchDocumentWithEventDataAccess {


}
