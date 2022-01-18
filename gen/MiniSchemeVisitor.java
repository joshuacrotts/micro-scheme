// Generated from C:/Users/Joshua/Desktop/Files/Java/MiniScheme/src/main/antlr4/com/joshuacrotts/minischeme\MiniScheme.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MiniSchemeParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MiniSchemeVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#miniScheme}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMiniScheme(MiniSchemeParser.MiniSchemeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDecl}
	 * labeled alternative in {@link MiniSchemeParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(MiniSchemeParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code procDecl}
	 * labeled alternative in {@link MiniSchemeParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcDecl(MiniSchemeParser.ProcDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDeclRead}
	 * labeled alternative in {@link MiniSchemeParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclRead(MiniSchemeParser.VarDeclReadContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(MiniSchemeParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#exprCons}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprCons(MiniSchemeParser.ExprConsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#exprSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSet(MiniSchemeParser.ExprSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#exprSetRead}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSetRead(MiniSchemeParser.ExprSetReadContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#exprOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprOp(MiniSchemeParser.ExprOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#exprVector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprVector(MiniSchemeParser.ExprVectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#exprList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList(MiniSchemeParser.ExprListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#exprCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprCall(MiniSchemeParser.ExprCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#exprLambdaDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLambdaDecl(MiniSchemeParser.ExprLambdaDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#exprLambdaDeclCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLambdaDeclCall(MiniSchemeParser.ExprLambdaDeclCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#exprIf}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprIf(MiniSchemeParser.ExprIfContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#exprCond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprCond(MiniSchemeParser.ExprCondContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#exprDo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprDo(MiniSchemeParser.ExprDoContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#exprLetDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLetDecl(MiniSchemeParser.ExprLetDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#exprLetNamed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLetNamed(MiniSchemeParser.ExprLetNamedContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#exprSymbol}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSymbol(MiniSchemeParser.ExprSymbolContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#exprSymbolComponent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSymbolComponent(MiniSchemeParser.ExprSymbolComponentContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#exprTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprTerm(MiniSchemeParser.ExprTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#exprBegin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprBegin(MiniSchemeParser.ExprBeginContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#procParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcParams(MiniSchemeParser.ProcParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#procBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcBody(MiniSchemeParser.ProcBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(MiniSchemeParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#lambdaParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaParams(MiniSchemeParser.LambdaParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#lambdaBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaBody(MiniSchemeParser.LambdaBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#lambdaArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaArgs(MiniSchemeParser.LambdaArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#letDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetDecl(MiniSchemeParser.LetDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#letBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetBody(MiniSchemeParser.LetBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#doDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoDecl(MiniSchemeParser.DoDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#doStepDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoStepDecl(MiniSchemeParser.DoStepDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#doTestDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoTestDecl(MiniSchemeParser.DoTestDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#doTrueExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoTrueExpr(MiniSchemeParser.DoTrueExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#doBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoBody(MiniSchemeParser.DoBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#seq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeq(MiniSchemeParser.SeqContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#condCond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondCond(MiniSchemeParser.CondCondContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#condBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondBody(MiniSchemeParser.CondBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#ifCond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfCond(MiniSchemeParser.IfCondContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#ifBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfBody(MiniSchemeParser.IfBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#ifElse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfElse(MiniSchemeParser.IfElseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp(MiniSchemeParser.OpContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#unaryop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryop(MiniSchemeParser.UnaryopContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#binaryop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryop(MiniSchemeParser.BinaryopContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#ternaryop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTernaryop(MiniSchemeParser.TernaryopContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#naryop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNaryop(MiniSchemeParser.NaryopContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#setop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetop(MiniSchemeParser.SetopContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#readop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadop(MiniSchemeParser.ReadopContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(MiniSchemeParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(MiniSchemeParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSchemeParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(MiniSchemeParser.ConstantContext ctx);
}