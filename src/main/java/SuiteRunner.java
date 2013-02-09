import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;

import java.util.ArrayList;
import java.util.List;

public class SuiteRunner extends ParentRunner<Runner> {
    private List<Runner> runners = new ArrayList<Runner>();

    public SuiteRunner(Class<?> klass) throws InitializationError {
        super(klass);
        try {
            List<Object> testInstances = (List<Object>) klass.getMethod("testInstances").invoke(null);
            for(Object i: testInstances){
                runners.add(new BlockJUnit4ClassRunnerForInstance(i));
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected List<Runner> getChildren() {
        return runners;
    }

    @Override
    protected Description describeChild(Runner child) {
        return child.getDescription();
    }

    @Override
    protected void runChild(Runner child, RunNotifier notifier) {
        child.run(notifier);
    }
}
