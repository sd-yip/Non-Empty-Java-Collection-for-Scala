package nejc4s

import java.lang
import java.util.function._

import nejc4s.base.{JavaLongStream, PositiveLong, PresentDouble, PresentLong}

trait NonEmptyJavaLongStream extends JavaLongStream.Refined {
  override def map(mapper: LongUnaryOperator): NonEmptyJavaLongStream
  override def mapToObj[B](mapper: LongFunction[_ <: B]): NonEmptyJavaStream[B]
  override def mapToInt(mapper: LongToIntFunction): NonEmptyJavaIntStream
  override def mapToDouble(mapper: LongToDoubleFunction): NonEmptyJavaDoubleStream
  override def distinct: NonEmptyJavaLongStream
  override def sorted: NonEmptyJavaLongStream
  override def peek(action: LongConsumer): NonEmptyJavaLongStream
  //override def toArray(): Array[Long]
  override def reduce(op: LongBinaryOperator): PresentLong
  override def min(): PresentLong
  override def max(): PresentLong
  override def count(): PositiveLong
  override def average(): PresentDouble
  override def findFirst(): PresentLong
  override def findAny(): PresentLong
  override def asDoubleStream: NonEmptyJavaDoubleStream
  override def boxed: NonEmptyJavaStream[lang.Long]
  override def sequential(): NonEmptyJavaLongStream
  override def parallel(): NonEmptyJavaLongStream
  override def unordered(): NonEmptyJavaLongStream
}

object NonEmptyJavaLongStream {
  trait UnsafeProxy extends JavaLongStream.UnsafeProxy with NonEmptyJavaLongStream {
    override def map(mapper: LongUnaryOperator): NonEmptyJavaLongStream =
      new NonEmptyJavaLongStream.UnsafeUnmodifiable(delegate.map(mapper))
    override def mapToObj[B](mapper: LongFunction[_ <: B]): NonEmptyJavaStream[B] =
      new NonEmptyJavaStream.UnsafeUnmodifiable(delegate.mapToObj(mapper))
    override def mapToInt(mapper: LongToIntFunction): NonEmptyJavaIntStream =
      new NonEmptyJavaIntStream.UnsafeUnmodifiable(delegate.mapToInt(mapper))
    override def mapToDouble(mapper: LongToDoubleFunction): NonEmptyJavaDoubleStream =
      new NonEmptyJavaDoubleStream.UnsafeUnmodifiable(delegate.mapToDouble(mapper))
    override def distinct: NonEmptyJavaLongStream = new NonEmptyJavaLongStream.UnsafeUnmodifiable(delegate.distinct)
    override def sorted: NonEmptyJavaLongStream = new NonEmptyJavaLongStream.UnsafeUnmodifiable(delegate.sorted)
    override def peek(action: LongConsumer): NonEmptyJavaLongStream =
      new NonEmptyJavaLongStream.UnsafeUnmodifiable(delegate.peek(action))
    //override def toArray(): Array[Long]
    override def reduce(op: LongBinaryOperator): PresentLong = PresentLong.unsafeFromOptionalLong(delegate.reduce(op))
    override def min(): PresentLong = PresentLong.unsafeFromOptionalLong(delegate.min())
    override def max(): PresentLong = PresentLong.unsafeFromOptionalLong(delegate.max())
    override def count(): PositiveLong = PositiveLong.unsafeFromLong(delegate.count())
    override def average(): PresentDouble = PresentDouble.unsafeFromOptionalDouble(delegate.average())
    override def findFirst(): PresentLong = PresentLong.unsafeFromOptionalLong(delegate.findFirst())
    override def findAny(): PresentLong = PresentLong.unsafeFromOptionalLong(delegate.findAny())
    override def asDoubleStream: NonEmptyJavaDoubleStream =
      new NonEmptyJavaDoubleStream.UnsafeUnmodifiable(delegate.asDoubleStream())
    override def boxed: NonEmptyJavaStream[lang.Long] = new NonEmptyJavaStream.UnsafeUnmodifiable(delegate.boxed)
    override def sequential(): NonEmptyJavaLongStream =
      new NonEmptyJavaLongStream.UnsafeUnmodifiable(delegate.sequential())
    override def parallel(): NonEmptyJavaLongStream = new NonEmptyJavaLongStream.UnsafeUnmodifiable(delegate.parallel())
    override def unordered(): NonEmptyJavaLongStream =
      new NonEmptyJavaLongStream.UnsafeUnmodifiable(delegate.unordered())
  }

  class UnsafeUnmodifiable(override protected val delegate: JavaLongStream) extends UnsafeProxy
}
