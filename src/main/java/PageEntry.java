public class PageEntry implements Comparable<PageEntry> {
    private final String pdfName;
    private final int page;
    private final int count;

    public PageEntry (String pdfName, int page, int count) {
        this.pdfName = pdfName;
        this.page = page;
        this.count = count;
    }
    public String getPdfName() {
        return pdfName;
    }
    public int getPage() {
        return page;
    }
    public int getCount() {
        return count;
    }
    @Override
    public int compareTo(PageEntry pageEntry) {
        return Integer.compare(pageEntry.getCount(), this.getCount());
    }
    @Override
    public String toString() {
        return "pdf=" + getPdfName() + ", page=" + getPage() + ", count=" + getCount();
    }
}
