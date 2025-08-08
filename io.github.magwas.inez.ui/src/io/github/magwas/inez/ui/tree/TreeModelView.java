package io.github.magwas.inez.ui.tree;

import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import io.github.magwas.inez.ui.editor.EditorInput;
import io.github.magwas.inez.ui.editor.ModelEditorView;
import jakarta.inject.Inject;

public class TreeModelView extends ViewPart {
	public static final String ID = "io.github.magwas.inez.ui.treeModelView";

	@Inject
	IWorkbench workbench;

	private TreeViewer viewer;

	@Override
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);

		viewer.setContentProvider(new ModelTreeContentProvider());
		viewer.setLabelProvider(new ModelTreeLabelProvider(workbench));
		viewer.setInput("parent");
		Transfer[] transfertypes = new Transfer[] {
				LocalSelectionTransfer.getTransfer() };
		int allDrops = DND.DROP_DEFAULT | DND.DROP_COPY | DND.DROP_LINK
				| DND.DROP_MOVE | DND.DROP_TARGET_MOVE;
		viewer.addDragSupport(allDrops, transfertypes,
				new TreeModelDragSourceLIstener(viewer));
		viewer.addDropSupport(allDrops, transfertypes,
				new TreeModelDropTargetListener(viewer));
		if (true) {
			try {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
						.openEditor(new EditorInput(), ModelEditorView.ID);
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

}