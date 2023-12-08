import view.IGameFrame;

/**
 * This class represents the mock view for testing the controller.
 */
public class MockView implements IGameFrame {
  private final StringBuffer log;

  public MockView(StringBuffer log) {
    this.log = log;
  }

  @Override
  public void refresh() {
    log.append("\nRefresh called\n");
  }

  @Override
  public void makeVisible() {
    log.append("\nMake Visible called\n");
  }

  @Override
  public void showGameStatus(boolean status) {
    log.append("\nGame Status called\n");
  }
}
