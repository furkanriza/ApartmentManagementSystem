import java.util.*;


public class ModelViewControllerConsole {

	public static void main(String[] args) throws Exception {
		// Connect to database
		connectToDatabase();

		
		// Model View Controller (MVC)
		// Router knows all the controllers
		Map<String, Controller> router = new HashMap<>();
		router.put("MainMenu", new Controller(new MainMenuView(), new NopModel()));
		router.put("Resident", new Controller(new ResidentView(), new ResidentModel()));
		router.put("Manager", new Controller(new ManagerView(), new ManagerModel()));
		router.put("ManagerController", new Controller(new ManagerControllerView(), new ManagerControllerModel()));
		router.put("AssistantManager", new Controller(new AssistantManagerView(), new AssistantManagerModel()));
		router.put("Transactions", new Controller(new TransactionsView(),new TransactionsModel()));

		ViewData viewData = new ViewData("Resident", "");
		do {
			// Model, View, and Controller
			Controller controller = router.get(viewData.functionName);
			System.out.println(viewData.functionName);
			//System.out.println(router.get(viewData.functionName));

			ModelData modelData = controller.executeModel(viewData);
			//System.out.println(viewData);
			System.out.println(modelData);

			viewData = controller.getView(modelData, viewData.functionName, viewData.operationName);
			//System.out.println(viewData.toString());
			
			System.out.println();
			System.out.println("-------------------------------------------------");
			System.out.println();
		}
		while (viewData.functionName != null);
		
		System.out.println();
		System.out.println();
		System.out.println("Program is ended...");


		// Disconnect from database
		disconnectFromDatabase();
	}

	
	public static void connectToDatabase() {
		DatabaseUtilities.host = "THE_MACHINE:65172";
		DatabaseUtilities.databaseName = "ApartmentManagementSystem2";
		
		try {
			DatabaseUtilities.getConnection();
		}
		catch(Exception e) {
			System.out.println("Exception occured : " + e);
			return;
		}		
	}
	
	public static void disconnectFromDatabase() {
		try {
			DatabaseUtilities.disconnect();
		}
		catch(Exception e) {
			System.out.println("Error disconnecting from database : " + e);
			return;
		}		
	}
	
}
