package listener;

import java.util.List;

public interface TaskCompleteListener {

    void onTaskComplete(List<String> output);

}
