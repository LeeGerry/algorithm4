package fb;

import java.util.ArrayDeque;
import java.util.Stack;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	public TreeNode(int x) {
		this.val = x;
	}
}

public class BstIterator {
	ArrayDeque<TreeNode> q;

	public BstIterator(TreeNode root) {
		q = new ArrayDeque<>();
		inorder(root);
	}
	/** 中序遍历*/
	private void inorder(TreeNode root) {
		TreeNode p = root;
		Stack<TreeNode> stack = new Stack<>();
		while (p != null || !stack.empty()) {
			if (p != null) {
				stack.push(p);
				p = p.left;
			} else {
				p = stack.pop();
				q.add(p);
				p = p.right;
			}
		}
	}

	/** whether we have a next smallest number */
	public boolean hasNext() {
		return !q.isEmpty();
	}

	/** the next smallest number */
	public int next() {
		if (hasNext())
			return q.poll().val;
		else
			return -1;
	}
}

class BstIterator2 {
	Stack<TreeNode> stack;

	public BstIterator2(TreeNode root) {
		stack = new Stack<>();
		while (root != null) {
			stack.push(root);
			root = root.left;
		}
	}

	/** whether we have a next smallest number */
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	/** the next smallest number */
	public int next() {
		TreeNode node = stack.pop();
		int result = node.val;
		if (node.right != null) {
			node = node.right;
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
		}
		return result;
	}
}
