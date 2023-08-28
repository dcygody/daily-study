package cn.zing.lingsuihua.demo1;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-28 09:48
 */
public class NodeService implements Processor<Node> {

    public NodeDTO nodeDTO;

    public NodeService(NodeDTO nodeDTO) {
        this.nodeDTO = nodeDTO;
    }

    @Override
    public Node execute() {
        pre();
        Node node = processor();
        post(node);
        return node;
    }

    @Override
    public void pre() {
        if (null != nodeDTO.getPreProcessor()) {
            nodeDTO.getPreProcessor().execute(nodeDTO);
        }
    }

    @Override
    public Node processor() {
        if (null != nodeDTO.getProcessor()) {
            return nodeDTO.getProcessor().execute(nodeDTO);
        }
        return null;
    }

    @Override
    public void post(Node t) {
        if (null != nodeDTO.getPostProcessor()) {
            nodeDTO.getPostProcessor().execute(t);
        }

    }
}


