package videos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class VideoPlatform {

    private final List<Channel> channels = new ArrayList<>();

    public List<Channel> getChannels() {
        return channels;
    }

    public void readDataFromFile(Path path) {
        String line;

        try {
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                while ((line = reader.readLine()) != null) {
                    if (!line.contains("channel;subscriptions;number_of_videos")) {
                        parseLine(line);
                    }
                }
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot open file for read!", ioe);
        }
    }


    private void parseLine(String line) {
        String[] fullLine = line.split(";");
        String channelName = fullLine[0];
        int sub = Integer.parseInt(fullLine[1]);
        int videoNumber = Integer.parseInt(fullLine[2]);

        channels.add(new Channel(channelName, sub, videoNumber));
    }

    public int calculateSumOfVideos() {
        return channels.stream()
                .mapToInt(Channel::getVideoNumber)
                .sum();

    }
}