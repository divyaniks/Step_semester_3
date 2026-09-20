package interfaces_and_abstraction.assignment_problems;

public class DataExportMain {

    public static void exportAll(Exportable[] items) {
        for (Exportable item : items) {
            System.out.println(item.exportData());
        }
    }

    public static void main(String[] args) {

        ReportGenerator report =
                new ReportGenerator("Quarterly Sales");

        UserProfile user =
                new UserProfile("abc");

        Exportable[] items = {
            report,
            user
        };

        exportAll(items);

        System.out.println(
            "Report Export Count: " +
            ReportGenerator.getExportCount()
        );
    }
}