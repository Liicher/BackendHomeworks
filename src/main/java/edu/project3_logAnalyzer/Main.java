package edu.project3_logAnalyzer;

// '$remote_addr - $remote_user [$time_local] ' '"$request" $status $body_bytes_sent ' '"$http_referer" "$user_agent"'
// java -jar nginx-log-stats.jar --path logs/2023* --from 2023-08-31 --format markdown
public class Main {
    private Main() {
    }

    public static void main(String[] args) {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.analyze(args);
    }
}
