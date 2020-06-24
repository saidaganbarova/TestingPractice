package utility;

public class Utility {



		
		
		
		public static void assertEquals(String expected, String actual) {
			
			if (actual.equals(expected)) {
				
				System.out.println("Passed");
			}else {
				System.out.println("Failed \n" + 
						"Expected: " + expected +"\n"+
						"Actual: " + actual);
				
			}
			
			
		}
		

		public static void assertContains(String expected, String actual) {
			
			if (actual.equals(expected)) {
				
				System.out.println("Passed");
			}else {
				System.out.println("Failed \n" + 
						"Expected: " + expected +"\n"+
						"Actual: " + actual);
				
			}
			
			
		}

	}


