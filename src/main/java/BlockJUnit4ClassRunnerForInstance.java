import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import java.util.List;

public class BlockJUnit4ClassRunnerForInstance extends BlockJUnit4ClassRunner{
    private final Object instance;

    public BlockJUnit4ClassRunnerForInstance(Object instance) throws InitializationError {
        super(instance.getClass());
        this.instance = instance;
    }

    @Override
    protected Object createTest() throws Exception {
        return instance;
    }

    @Override
    protected void collectInitializationErrors(List<Throwable> errors) {
        validateNoNonStaticInnerClass(errors);
        validateInstanceMethods(errors);
        validatePublicVoidNoArgMethods(BeforeClass.class, true, errors);
        validatePublicVoidNoArgMethods(AfterClass.class, true, errors);
    }
}
