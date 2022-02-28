package videos;

public class Channel {

    private String channelName;
    private int sub;
    private int videoNumber;

    public Channel(String channelName, int sub, int videoNumber) {
        this.channelName = channelName;
        this.sub = sub;
        this.videoNumber = videoNumber;
    }

    public String getChannelName() {
        return channelName;
    }

    public int getSub() {
        return sub;
    }

    public int getVideoNumber() {
        return videoNumber;
    }
}