import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
/*A multithreaded web crawler is a program that can search the internet and collect information from websites using many threads at once, making it faster and more efficient.
 It starts with a URL and adds all the links on the page to a queue. Then, it uses many threads to extract the contents of the pages in the queue, and each thread collects the relevant data.
  As each thread finishes, it stores the data in a database or file. The program continues to collect links and data until all the links in the queue are processed.

Multithreaded web crawlers are useful for many things, like searching the internet for information or analyzing websites.
 But there are challenges to building them, like managing multiple threads, handling errors, and being respectful of the websites they visit.*/

public class Question7B {
    // Set up the initial queue with the starting URLs
    private static BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    static {
        queue.add("https://example.com");
    }

    // Set up the maximum number of threads to be used
    private static final int MAX_THREADS = 10;

    // Set up a HashSet to keep track of visited URLs
    private static HashSet<String> visited = new HashSet<>();

    public static void main(String[] args) throws InterruptedException {
        // Create the threads and start them
        Thread[] threads = new Thread[MAX_THREADS];
        for (int i = 0; i < MAX_THREADS; i++) {
            threads[i] = new Thread(new Crawler());
            threads[i].start();
        }

        // Wait for all tasks to be completed
        for (Thread thread : threads) {
            thread.join();
        }
    }

    private static class Crawler implements Runnable {
        public void run() {
            while (true) {
                try {
                    // Get the next URL from the queue
                    String url = queue.take();

                    // Check if the URL has already been visited
                    if (visited.contains(url)) {
                        continue;
                    }

                    // Make a request to the URL
                    URL urlObj = new URL(url);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(urlObj.openStream()));

                    // Extract links from the response
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("href=")) {
                            int startIndex = line.indexOf("href=") + 6;
                            int endIndex = line.indexOf("\"", startIndex);
                            String link = line.substring(startIndex, endIndex);

                            // Convert relative URLs to absolute URLs
                            if (!link.startsWith("http")) {
                                link = urlObj.getProtocol() + "://" + urlObj.getHost() + link;
                            }

                            // Add the link to the queue if it hasn't been visited yet
                            if (!visited.contains(link)) {
                                queue.put(link);
                            }
                        }
                    }

                    // Mark the URL as visited
                    visited.add(url);
                } catch (Exception ignore) {}
            }
        }
    }
}