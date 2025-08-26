package io.github.magwas.inez.ui;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IPlaceholderFolderLayout;

import io.github.magwas.inez.ui.editor.ModelEditorView;
import io.github.magwas.inez.ui.tree.TreeModelView;

public class Perspective implements IPerspectiveFactory {

	public static final String ID = "io.github.magwas.inez.ui.perspective";

	private static final String LEFT_TOP = "Left top";

	private IFolderLayout folderLayoutLeftTop;
	private IFolderLayout folderLayoutLeftBottom;
	private IPlaceholderFolderLayout folderLayoutRight;
	private IFolderLayout folderLayoutBottom;

	@Override
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(true);
		layout.setFixed(true);

		folderLayoutLeftTop = layout.createFolder(LEFT_TOP, IPageLayout.LEFT, .23f,
				IPageLayout.ID_EDITOR_AREA);
		folderLayoutLeftBottom = layout.createFolder("Left bottom",
				IPageLayout.BOTTOM, .6f, LEFT_TOP);
		folderLayoutRight = layout.createPlaceholderFolder("right",
				IPageLayout.RIGHT, .85f, IPageLayout.ID_EDITOR_AREA);
		folderLayoutBottom = layout.createFolder("bottom", IPageLayout.BOTTOM, .7f,
				IPageLayout.ID_EDITOR_AREA);

		folderLayoutLeftTop.addView(TreeModelView.ID);

		folderLayoutBottom.addView(ModelEditorView.ID);

		// Outline View
//    folderLayoutLeftBottom.addView(ViewManager.OUTLINE_VIEW);

		// Navigator View
		// folderLayoutLeftBottom.addView(INavigatorView.ID);

		// Palette View
		folderLayoutRight.addPlaceholder("org.eclipse.gef.ui.palette_view"); //$NON-NLS-1$

		// Placeholder for all other views
		folderLayoutBottom.addPlaceholder("*"); //$NON-NLS-1$

	}

}
