package pl.bgolc.tachograph;

import org.postgresql.util.PGInterval;

public class Main {


	public static void main(String[] args) {
//        DataResolver dataResolver = DataResolverImpl.getInstance();
//        dataResolver.downloadDataFromFile("C:\\Users\\bgolc\\Documents\\Docs\\PKK_cze_2017.csv");
//        Inspector inspector = Inspector.getInstance();
//        inspector.setData(dataResolver.getData());
                
//        inspector.checkData();

        PGInterval interval = new PGInterval();

        interval.setHours(12);
        interval.setMinutes(56);

        System.out.println(interval);

//        inspector.displayDayList();
//        inspector.displayWeeksOfWork();

/*        List<Integer> list = new ArrayList<Integer>();

        Integer three = 3;
        Integer five = 5;
        list.add(1);
        list.add(2);
        list.add(three);
        list.add(4);
        list.add(five);
        list.add(6);

        list.add(3, 10);

        System.out.println(list);

//        list.remove(1);
//
//        System.out.println(list);
//
//        list.remove(three);
//        list.remove(five);
//
//        System.out.println(list);
        List<Integer> tempList = new ArrayList<Integer>();
        tempList.addAll(list);

        for (int i=0; i<tempList.size(); i++) {
            if (!list.isEmpty())
                list.remove(0);
            System.out.println(list);
            System.out.println(tempList.size());
        }*/

        //TODO w controlerze przekazac dane do Serwisu zapisującego danego na bazie lub zrobić to w klasie DataResolverImpl, ale chyba bedzie to mniej czytelne
        
//        inspector.displayWeeksOfWork();
        
        
//        dataResolver.readData();

/*        inspector.checkData();
        inspector.checkTwoNextWeeks();
        List<Data> data = inspector.getDataOutput();*/

//        int i = 0;
/*        for (Data temp : data) {
            if (temp.getMisdemeanors().isExceededMaxWeeklyDriveTimeTwoWeeks() == true) {
//                System.out.println("wykroczenie czasu jazdy na 2 tyg" + i++);
            } else {
//                System.out.println("brak wykroczenia" + i++);
            }
        }*/


//        inspector.displayAllData();		
	}
}
