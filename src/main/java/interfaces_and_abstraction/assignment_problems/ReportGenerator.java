package interfaces_and_abstraction.assignment_problems;

public class ReportGenerator implements Exportable {

    private static int exportCount = 0;

    private String reportName;

    public ReportGenerator(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public String exportData() {
        exportCount++;
        return "Report exported: " + reportName;
    }

    public static int getExportCount() {
        return exportCount;
    }
}