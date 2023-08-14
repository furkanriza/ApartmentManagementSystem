import java.util.*;

class MainMenuView implements ViewInterface {

	@Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {

		Integer choice,choice1;
		do {
			// make an inner do while that returns which table to change as parameter to outer while loop and parameter to viewData (function name)
			do{
				System.out.println("1. Operation at Residents");
				System.out.println("2. Operation at Manager");
				System.out.println("3. Operation at ManagerController");
				System.out.println("4. Operation at AssistantManager");
				System.out.println("5. Operation at Transactions");
				System.out.println();
				choice1 = getInteger("Enter your choice : ", false);
			}while(choice1 ==null || choice1< 1||choice1 >5 );
			System.out.println("1. Show all");
			System.out.println("2. Show with parameters");
			System.out.println("3. Add ");
			System.out.println("4. Update ");
			System.out.println("5. Delete ");
			System.out.println("6. Quit");
			System.out.println();

			choice = getInteger("Enter your choice : ", false);
		} 
		while (choice == null || choice < 1 || choice > 6);
		
		
		Map<String, Object> userInput = new HashMap<>();
		userInput.put("mainMenuChoice", choice);
		
		switch (choice.intValue()) {
		case 1: operationName = "select";		break;
		case 2: operationName = "select.gui";	break;
		case 3: operationName = "insert.gui";	break;
		case 4: operationName = "update.gui";	break;
		case 5: operationName = "delete.gui";	break;
		default: return new ViewData(null, null);
		}
		switch (choice1.intValue()){
			case 1: return new ViewData("Resident", operationName, new HashMap<>());
			case 2: return new ViewData("Manager", operationName, new HashMap<>());
			case 3: return new ViewData("ManagerController", operationName, new HashMap<>());
			case 4: return new ViewData("AssistantManager", operationName, new HashMap<>());
			case 5: return new ViewData("Transactions", operationName, new HashMap<>());
			default: return new ViewData(null, null);
		}

	}

	@Override
	public String toString() {
		return "Main Menu View";
	}		
}
