package view.swing.other.google;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.BoundedRangeModel;
import javax.swing.Box;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import java.awt.Font;

public final class MainPanelPb extends JPanel implements HierarchyListener {
	private static final long serialVersionUID = 5739524837659685903L;
	private transient SwingWorker<String, Void> worker;

	private MainPanelPb() {
		super(new BorderLayout());
		UIManager.put("ProgressBar.cycleTime", 1000);
		UIManager.put("ProgressBar.repaintInterval", 10);

		BoundedRangeModel model = new DefaultBoundedRangeModel();
		JProgressBar progress1 = new JProgressBar(model);
		progress1.setUI(new StripedProgressBarUI(true, true));

		JProgressBar progress2 = new JProgressBar(model);
		progress2.setUI(new StripedProgressBarUI(true, false));

		JProgressBar progress3 = new JProgressBar(model);
		progress3.setUI(new StripedProgressBarUI(false, true));

		JProgressBar progress4 = new JProgressBar(model);
		progress4.setUI(new StripedProgressBarUI(false, false));

		List<JProgressBar> list = Arrays.asList(new JProgressBar(model), progress1, progress2, progress3, progress4);

		JPanel p = new JPanel(new GridLayout(5, 1));
		list.forEach(bar -> p.add(makePanel(bar)));

		JButton btnPrimary = new JButton("Primary");
		btnPrimary.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnPrimary.addActionListener(e -> {
			if (Objects.nonNull(worker) && !worker.isDone()) {
				worker.cancel(true);
			}
			worker = new BackgroundTask();
			list.forEach(bar -> {
				bar.setIndeterminate(true);
				worker.addPropertyChangeListener(new ProgressListener(bar));
			});
			worker.execute();
		});

		Box box = Box.createHorizontalBox();
		box.add(Box.createHorizontalGlue());
		box.add(btnPrimary);
		box.add(Box.createHorizontalStrut(5));

		addHierarchyListener(this);
		add(p);
		add(box, BorderLayout.SOUTH);
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setPreferredSize(new Dimension(320, 240));
	}

	@Override
	public void hierarchyChanged(HierarchyEvent e) {
		boolean isDisplayableChanged = (e.getChangeFlags() & HierarchyEvent.DISPLAYABILITY_CHANGED) != 0;
		if (isDisplayableChanged && !e.getComponent().isDisplayable() && Objects.nonNull(worker)) {
			System.out.println("DISPOSE_ON_CLOSE");
			worker.cancel(true);
			worker = null;
		}
	}

	private static Component makePanel(Component cmp) {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 1d;

		JPanel p = new JPanel(new GridBagLayout());
		p.add(cmp, c);
		return p;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(MainPanelPb::createAndShowGui);
	}

	private static void createAndShowGui() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
			Toolkit.getDefaultToolkit().beep();
		}
		JFrame frame = new JFrame("@title@");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(new MainPanelPb());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}

class BackgroundTask extends SwingWorker<String, Void> {
	@Override
	public String doInBackground() {
		try { // dummy task
			Thread.sleep(5000);
		} catch (InterruptedException ex) {
			return "Interrupted";
		}
		int current = 0;
		int lengthOfTask = 100;
		while (current <= lengthOfTask && !isCancelled()) {
			try { // dummy task
				Thread.sleep(50);
			} catch (InterruptedException ex) {
				return "Interrupted";
			}
			setProgress(100 * current / lengthOfTask);
			current++;
		}
		return "Done";
	}
}

class ProgressListener implements PropertyChangeListener {
	private final JProgressBar progressBar;

	protected ProgressListener(JProgressBar progressBar) {
		this.progressBar = progressBar;
		this.progressBar.setValue(0);
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		String strPropertyName = e.getPropertyName();
		if ("progress".equals(strPropertyName)) {
			progressBar.setIndeterminate(false);
			int progress = (Integer) e.getNewValue();
			progressBar.setValue(progress);
		}
	}
}
